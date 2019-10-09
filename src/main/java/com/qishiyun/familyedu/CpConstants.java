package com.qishiyun.familyedu;

import java.math.BigDecimal;
import java.util.List;

/**
 * 定义本服务的常量
 *
 * @author poplar
 * @date 2019/6/20
 */
public interface CpConstants {
    /**
     * 成功标记
     */
    Integer SUCCESS = 0;
    /**
     * 失败标记
     */
    Integer FAIL = 1;

    /**
     * 报告正在写状态字段
     */
    Integer UNFINISHED = 0;

    /**
     * 报告测谎题有效性
     */

    String VALIDITY = "1";
    //小学
    // 教育测评逆向得分题Id
    int[] REVERSE_QUESTION_PRIMARY = new int[]{27,28,34,35,37,38,53,54,55,57,58,59,60,61,62,76,77,78,79,80,84,86};
    //任务选择题Id和answer
    String TASK_QUESTION_PRIMARY ="44:1,45:2,46:3,47:3,48:3";
    //应答真诚度题
    int[] HONEST_QUESTION_PRIMARY =  new int[]{37,53,72,85};
    //中学
    // 教育测评逆向得分题Id
    int[] REVERSE_QUESTION_JUNIOR = new int[]{27,28,34,35,37,38,53,54,55,57,58,59,60,61,62,76,77,78,79,80,84,86,90,92,93,96,97};
    //任务选择题Id和answer
    String  TASK_QUESTION_JUNIOR ="64:3,65:3,66:2,67:3,68:3,69:3,70:2,71:3";
    //应答真诚度题
    int[] HONEST_QUESTION_JUNIOR =  new int[]{31,63,75,91};

    int BASE_QUENTINO_END = 17;

    int NO_SELECTE_QUENTION_ID = 2;

    String SCORE_0= "0";
    String SCORE_1= "1";
    String SCORE_2= "2";
    String SCORE_3= "3";
    String SCORE_4= "4";
    String SCORE_5= "5";
    String SCORE_6= "6";

    String ANSWER_1= "1";
    String ANSWER_2= "2";
    String ANSWER_3= "3";
    String ANSWER_4= "4";
    String ANSWER_5= "5";
    String ANSWER_6= "6";


    String TASK_SCORE_1 = "1";
    String TASK_SCORE_0= "0";
    //应答真诚度判定标准
    BigDecimal HONEST_LIMIT = new BigDecimal("20");

    String HONEST_KEY = "应答真诚度得分";

    String HONEST_LEVEL_KEY = "作答掩饰性水平";

    String[] FILTER_DEMENSION_NAME = {"家庭环境","养育素养","亲子互动"};

    String FAMILY_EDU_NAME = "家庭教育";

    String SCORE_SUFFIX = "得分";

    String LEAVE_SUFFIX ="水平";

    int THREAD_NUM = 4;

    int PRIMARARY_NUM = 92;

    int JONIOR_NUM = 98;

    int PRIMARY_QUESTION_START  =10002714;

    int PRIMARY_QUESTION_END  =10002805;

    int PRIMARY_SECOND_QUESTION = 10002715;

    int JUUNIOR_QUESTION_START = 10002806;
    int JUNIOR_QUESTION_END = 10002903;
    int JUNOOR_SECOND_QUESTION = 10002807;

    int[] PRIMARY_HONEST_QIESTION = new int[]{10002750,10002766,10002785,10002798};

    int[] JUNIOR_HONEST_QIESTION = new int[]{10002836,10002868,10002880,10002896};











}
