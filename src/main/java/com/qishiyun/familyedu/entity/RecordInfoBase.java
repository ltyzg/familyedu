package com.qishiyun.familyedu.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class RecordInfoBase implements Serializable {
    //private String recordId;
    private Integer questionId;
    private String answer;
    private Float score;
}
