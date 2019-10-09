package com.qishiyun.familyedu.threads;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qishiyun.familyedu.CpConstants;
import com.qishiyun.familyedu.entity.RecordBase;
import com.qishiyun.familyedu.entity.RecordInfoBase;
import com.qishiyun.familyedu.entity.Score;
import com.qishiyun.familyedu.service.TRecordInfoService;
import com.qishiyun.familyedu.serviceImpl.TRecordInfoServiceImpl;
import com.qishiyun.familyedu.util.DateUtils;
import com.qishiyun.familyedu.util.SpringUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

@Slf4j
public class FamilyTask implements Runnable{
    private CountDownLatch countDownLatch;
    private TRecordInfoService tRecordInfoService;
    private List<RecordBase> recordList;
    private final ObjectMapper objectMapper = new ObjectMapper();
    List<Object> resultList;
    int questionStart ;
    int questionEnd;
    int secordQuestion;
    private List<Integer> honestIds;

    public FamilyTask(CountDownLatch countDownLatch, List<RecordBase> recordList, List<Object> resultList,TRecordInfoService tRecordInfoService, int questionStart, int questionEnd, int secordQuestion, List<Integer> honestIds) {
        this.countDownLatch = countDownLatch;
        this.tRecordInfoService = tRecordInfoService;
        this.recordList = recordList;
        this.resultList = resultList;
        this.questionStart = questionStart;
        this.questionEnd = questionEnd;
        this.secordQuestion = secordQuestion;
        this.honestIds = honestIds;
    }

    @Override
    public void run() {
        try {
            getRecordResult(resultList,recordList);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        } finally {
            if(countDownLatch != null) {
                countDownLatch.countDown();
            }
        }
    }

    private void getRecordResult(List<Object> resultList, List<RecordBase> recordList) throws IOException {
        for (RecordBase recordBase : recordList) {
            LinkedHashMap<String, Object> recordMap =new LinkedHashMap<>();
            BigDecimal honestScore = BigDecimal.ZERO;//应答真诚度得分，默认0
            int concealLevel = 2;//做题掩饰性水平 默认较低
            String recordId = recordBase.getRecordId();
            getFirstResult(recordMap,recordBase,honestScore,concealLevel);
            log.info("record_id[{}]开始解析题目",recordId);
            honestScore =getQuestionResult(recordMap,recordId,honestScore);
            if(CpConstants.HONEST_LIMIT.compareTo(honestScore)>0){
                concealLevel =1;
            }
                log.info("record_id[{}]完成解析题目",recordId);
                recordMap.put(CpConstants.HONEST_KEY,honestScore);
                recordMap.put(CpConstants.HONEST_LEVEL_KEY,concealLevel);
                String scoreInfo = recordBase.getScoreInfo();
                List<String> filterName = Arrays.asList(CpConstants.FILTER_DEMENSION_NAME);
                log.info("record_id[{}]开始解析维度",recordId);
                getDimensionResult(recordMap,scoreInfo,filterName,recordId);
                log.info("record_id[{}]完成解析维度",recordId);

                resultList.add(recordMap);
            }

        }

    private void createReordMap(LinkedHashMap<String, Object> recordMap) {
        int init = 0;
        for (int i = questionStart; i <questionEnd+1 ; i++) {
            recordMap.put(String.valueOf(i),init);
        }

    }

    private void getDimensionResult(LinkedHashMap<String,Object> recordMap, String scoreInfo, List<String> filterName, String recordId) throws IOException {
        JSONObject jsonObject = JSONObject.parseObject(scoreInfo);
        String scoreObject = jsonObject.get("score").toString();
        List<Score> scoreList = objectMapper.readValue(scoreObject, new TypeReference<List<Score>>() {
        });
        List<Score> filterList = scoreList.stream().filter((Score s) -> !(filterName.contains(s.getTreename())))
                .sorted(Comparator.comparing(Score::getTreeid)).collect(Collectors.toList());
        for (Score score : filterList) {
            String rawName = score.getTreename();
            String finalName ;
            if("总分".equals(rawName)){
                finalName = CpConstants.FAMILY_EDU_NAME+CpConstants.SCORE_SUFFIX;
            }else{
                finalName = rawName+CpConstants.SCORE_SUFFIX;
            }
            BigDecimal rawScore = score.getScore();
            BigDecimal finalScore = BigDecimal.ZERO;
            if(rawScore != null){
                finalScore= rawScore.setScale(1,RoundingMode.HALF_UP);
            }
            recordMap.put(finalName,finalScore);
        }
        for (Score score : filterList) {
            String rawName = score.getTreename();
            String finalName ;
            if("总分".equals(rawName)){
                finalName = CpConstants.FAMILY_EDU_NAME+CpConstants.LEAVE_SUFFIX;
            }else{
                finalName = rawName+CpConstants.LEAVE_SUFFIX;
            }
            String rangeLevel = score.getRangeName();
            recordMap.put(finalName,rangeLevel);
        }
    }

    private BigDecimal getQuestionResult1(LinkedHashMap<String, Object> recordMap, String recordId, Map<Integer, String> taskMap, List<Integer> reverseIds, List<Integer> honestIds, BigDecimal honestScore, List<RecordInfoBase> recordInfoList) {
        for (int i = 1; i <recordInfoList.size()+1 ; i++) {
            String selectKey = "第"+i+"题选项";
            String scoreKey = "第"+i+"题得分";
            /*if(i>infoSize){
                recordMap.put(scoreKey,0);
                continue;
            }*/
            int index = i-1;
            RecordInfoBase recordInfoBase = recordInfoList.get(index);
            String answer = recordInfoBase.getAnswer();
            String score ;
            //处理基本信息题
            if(CpConstants.BASE_QUENTINO_END >= i){
                recordMap.put(selectKey,answer);
            }else{
                //处理非基本信息题
                score =answer;
                //处理反向题
                if(reverseIds.contains(i)){
                    score =getReverseScore(answer);
                }
                //处理任务先择题
                if(taskMap.containsKey(i)){
                    String rigitAnswer= taskMap.get(i);
                    if(rigitAnswer.equals(answer)){
                        score = CpConstants.TASK_SCORE_1;
                    }else{
                        score = CpConstants.TASK_SCORE_0;
                    }
                }
                recordMap.put(scoreKey,score);
            }
            //应答真诚度得分
            if(honestIds.contains(i)){
                BigDecimal answerScore = new BigDecimal(answer);
                honestScore= honestScore.add(answerScore);
            }
        }
        return honestScore;
    }

    private BigDecimal getQuestionResult(LinkedHashMap<String, Object> recordMap, String recordId, BigDecimal honestScore) {
        List<RecordInfoBase> recordInfoList = tRecordInfoService.getRecordInfoList(recordId);
        for (RecordInfoBase recordInfoBase : recordInfoList) {
            Integer questionId = recordInfoBase.getQuestionId();
            String key = String.valueOf(questionId);
            Float score = recordInfoBase.getScore();
            if(questionStart == questionId || secordQuestion == questionId){
                String value = recordInfoBase.getAnswer();
                recordMap.put(key,value);
            }else{
                recordMap.put(key,score);
            }
            if(honestIds.contains(questionId)){
                BigDecimal answerScore = new BigDecimal(score);
                honestScore= honestScore.add(answerScore);
            }
        }
        return honestScore;
    }

    private String getReverseScore(String answer) {
        if(CpConstants.ANSWER_1.equals(answer)){
            return CpConstants.SCORE_6;
        }
        if(CpConstants.ANSWER_2.equals(answer)){
            return CpConstants.SCORE_5;
        }
        if(CpConstants.ANSWER_3.equals(answer)){
            return CpConstants.SCORE_4;
        }
        if(CpConstants.ANSWER_4.equals(answer)){
            return CpConstants.SCORE_3;
        }
        if(CpConstants.ANSWER_5.equals(answer)){
            return CpConstants.SCORE_2;
        }
        if(CpConstants.ANSWER_6.equals(answer)){
            return CpConstants.SCORE_1;
        }
        return CpConstants.SCORE_0;
    }

    private void getFirstResult(LinkedHashMap<String,Object> recordMap, RecordBase recordBase, BigDecimal honestScore, int concealLevel) {
        String createTime =DateUtils.DateToString(recordBase.getCreateTime()) ;//测试时间
        Integer usedTime = recordBase.getUsedTime();//做测试的时间
        recordMap.put("测试时间",createTime);
        recordMap.put("答题总时长（秒）",usedTime);
        recordMap.put(CpConstants.HONEST_KEY,honestScore);
        recordMap.put(CpConstants.HONEST_LEVEL_KEY,concealLevel);
        createReordMap(recordMap);
    }
}
