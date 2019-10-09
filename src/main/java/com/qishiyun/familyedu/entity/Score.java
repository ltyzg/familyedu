package com.qishiyun.familyedu.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Score implements Serializable {
    private int treeid;
    private BigDecimal score;
    private String treename;
    private String rangeInfo;
    private String suggestion;
    private String rangeName;
}
