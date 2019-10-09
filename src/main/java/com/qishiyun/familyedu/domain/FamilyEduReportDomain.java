package com.qishiyun.familyedu.domain;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qishiyun.familyedu.CpConstants;
import com.qishiyun.familyedu.entity.*;
import com.qishiyun.familyedu.enums.ErrorCodeEnum;
import com.qishiyun.familyedu.service.TRecordInfoService;
import com.qishiyun.familyedu.service.TRecordService;
import com.qishiyun.familyedu.threads.FamilyTask;
import com.qishiyun.familyedu.util.ListUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/familyReport")
@Slf4j
public class FamilyEduReportDomain {

    @Autowired
    TRecordInfoService recordInfoService;
    @Autowired
    TRecordService recordService;

    @GetMapping("/primary")
    public Object getFamilyReportPrimary() throws IOException, InterruptedException {
        log.info("小学开始查询records");
        List<RecordBase> recordList =recordService.getPrimaryRecordList();
        if(recordList ==null || recordList.size()==0){
            return ErrorCodeEnum.CP10000001.msg();
        }
        log.info("小学结束查询records,records_size[{}]",recordList.size());
        List<Integer> honestIds = Arrays.stream(CpConstants.PRIMARY_HONEST_QIESTION).boxed().collect(Collectors.toList());
        log.info("小学家庭教育测试开始");
        int threadNum = CpConstants.THREAD_NUM;
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        Executor executor = Executors.newFixedThreadPool(threadNum);
        List<Object> resultList = new ArrayList<>();
        int recordSize = recordList.size();
        int size = recordSize / threadNum;
        List<List<RecordBase>> partition = ListUtils.partition(recordList, size);
        List<RecordBase> recordList1 = partition.get(0);
        List<RecordBase> recordList2 = partition.get(1);
        List<RecordBase> recordList3 = partition.get(2);
        List<RecordBase> recordList4 = partition.get(3);
        /*List<RecordBase> recordList5 = partition.get(4);
        List<RecordBase> recordList6 = partition.get(5);
        List<RecordBase> recordList7 = partition.get(6);
        List<RecordBase> recordList8 = partition.get(7);
        List<RecordBase> recordList9 = partition.get(8);
        List<RecordBase> recordList10 = partition.get(9);*/
        int primaryQuestionStart = CpConstants.PRIMARY_QUESTION_START;
        int primaryQuestionEnd = CpConstants.PRIMARY_QUESTION_END;
        int primarySecondQuestion = CpConstants.PRIMARY_SECOND_QUESTION;
        executor.execute(new FamilyTask(countDownLatch,recordList1,resultList,recordInfoService,primaryQuestionStart,primaryQuestionEnd,primarySecondQuestion ,honestIds));
        executor.execute(new FamilyTask(countDownLatch,recordList2,resultList,recordInfoService,primaryQuestionStart,primaryQuestionEnd,primarySecondQuestion ,honestIds));
        executor.execute(new FamilyTask(countDownLatch,recordList3,resultList,recordInfoService,primaryQuestionStart,primaryQuestionEnd,primarySecondQuestion ,honestIds));
        executor.execute(new FamilyTask(countDownLatch,recordList4,resultList,recordInfoService,primaryQuestionStart,primaryQuestionEnd,primarySecondQuestion ,honestIds));
        /*executor.execute(new FamilyTask(countDownLatch,recordList5,resultList,recordInfoService,primaryQuestionStart,primaryQuestionEnd,primarySecondQuestion ,honestIds));
        executor.execute(new FamilyTask(countDownLatch,recordList6,resultList,recordInfoService,primaryQuestionStart,primaryQuestionEnd,primarySecondQuestion ,honestIds));
        executor.execute(new FamilyTask(countDownLatch,recordList7,resultList,recordInfoService,primaryQuestionStart,primaryQuestionEnd,primarySecondQuestion ,honestIds));
        executor.execute(new FamilyTask(countDownLatch,recordList8,resultList,recordInfoService,primaryQuestionStart,primaryQuestionEnd,primarySecondQuestion ,honestIds));
        executor.execute(new FamilyTask(countDownLatch,recordList9,resultList,recordInfoService,primaryQuestionStart,primaryQuestionEnd,primarySecondQuestion ,honestIds));
        executor.execute(new FamilyTask(countDownLatch,recordList10,resultList,recordInfoService,primaryQuestionStart,primaryQuestionEnd,primarySecondQuestion ,honestIds));*/
        countDownLatch.await();
        log.info("小学最终数据量size[{}]",resultList.size());
        Object result = JSONObject.toJSON(resultList);
        return result;
    }

    @GetMapping("/junior")
    public Object getFamilyReportJunior() throws IOException, InterruptedException {
        log.info("初中开始查询records");
        List<RecordBase> recordList =recordService.getJuniorRecordList();
        if(recordList ==null || recordList.size()==0){
            return ErrorCodeEnum.CP10000001.msg();
        }
        log.info("初中结束查询records,records_size[{}]",recordList.size());
       /* String[] taskStr = CpConstants.TASK_QUESTION_JUNIOR.split(",");
        List<Integer> reverseIds = Arrays.stream(CpConstants.REVERSE_QUESTION_JUNIOR).boxed().collect(Collectors.toList());
        List<Integer> honestIds = Arrays.stream(CpConstants.HONEST_QUESTION_JUNIOR).boxed().collect(Collectors.toList());
        Map<Integer, String> taskMap = getTaskMap(taskStr);*/
        List<Integer> honestIds = Arrays.stream(CpConstants.JUNIOR_HONEST_QIESTION).boxed().collect(Collectors.toList());
        log.info("初中家庭教育测试开始");
        int threadNum = CpConstants.THREAD_NUM;
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        Executor executor = Executors.newFixedThreadPool(threadNum);
        List<Object> resultList = new ArrayList<>();
        int recordSize = recordList.size();
        int size = recordSize / threadNum;
        int juuniorQuestionStart = CpConstants.JUUNIOR_QUESTION_START;
        int juniorQuestionEnd = CpConstants.JUNIOR_QUESTION_END;
        int junoorSecondQuestion = CpConstants.JUNOOR_SECOND_QUESTION;
        List<List<RecordBase>> partition = ListUtils.partition(recordList, size);
        List<RecordBase> recordList1 = partition.get(0);
        List<RecordBase> recordList2 = partition.get(1);
        List<RecordBase> recordList3 = partition.get(2);
        List<RecordBase> recordList4 = partition.get(3);
        /*List<RecordBase> recordList5 = partition.get(4);
        List<RecordBase> recordList6 = partition.get(5);
        List<RecordBase> recordList7 = partition.get(6);
        List<RecordBase> recordList8 = partition.get(7);
        List<RecordBase> recordList9 = partition.get(8);
        List<RecordBase> recordList10 = partition.get(9);*/
        executor.execute(new FamilyTask(countDownLatch,recordList1,resultList,recordInfoService,juuniorQuestionStart,juniorQuestionEnd,junoorSecondQuestion,honestIds));
        executor.execute(new FamilyTask(countDownLatch,recordList2,resultList,recordInfoService,juuniorQuestionStart,juniorQuestionEnd,junoorSecondQuestion,honestIds));
        executor.execute(new FamilyTask(countDownLatch,recordList3,resultList,recordInfoService,juuniorQuestionStart,juniorQuestionEnd,junoorSecondQuestion,honestIds));
        executor.execute(new FamilyTask(countDownLatch,recordList4,resultList,recordInfoService,juuniorQuestionStart,juniorQuestionEnd,junoorSecondQuestion,honestIds));
        /*executor.execute(new FamilyTask(countDownLatch,recordList5,resultList,recordInfoService,juuniorQuestionStart,juniorQuestionEnd,junoorSecondQuestion,honestIds));
        executor.execute(new FamilyTask(countDownLatch,recordList6,resultList,recordInfoService,juuniorQuestionStart,juniorQuestionEnd,junoorSecondQuestion,honestIds));
        executor.execute(new FamilyTask(countDownLatch,recordList7,resultList,recordInfoService,juuniorQuestionStart,juniorQuestionEnd,junoorSecondQuestion,honestIds));
        executor.execute(new FamilyTask(countDownLatch,recordList8,resultList,recordInfoService,juuniorQuestionStart,juniorQuestionEnd,junoorSecondQuestion,honestIds));
        executor.execute(new FamilyTask(countDownLatch,recordList9,resultList,recordInfoService,juuniorQuestionStart,juniorQuestionEnd,junoorSecondQuestion,honestIds));
        executor.execute(new FamilyTask(countDownLatch,recordList10,resultList,recordInfoService,juuniorQuestionStart,juniorQuestionEnd,junoorSecondQuestion,honestIds));*/
        countDownLatch.await();
        log.info("初中最终数据量size[{}]",resultList.size());
        Object result = JSONObject.toJSON(resultList);
        return result;
    }



    private Map<Integer,String> getTaskMap(String[] taskStr) {
        Map<Integer, String> taskMap = new HashMap<>();
        for (String task : taskStr) {
            String[] taskArr = task.split(":");
            taskMap.put(Integer.valueOf(taskArr[0]),taskArr[1]);
        }
        return taskMap;
    }
}
