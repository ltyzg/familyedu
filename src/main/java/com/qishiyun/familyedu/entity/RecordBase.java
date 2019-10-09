package com.qishiyun.familyedu.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class RecordBase implements Serializable {
    private String recordId;
    private Date createTime;
    /*private Date finishTime;*/
    private Integer usedTime;
    private String scoreInfo;

}
