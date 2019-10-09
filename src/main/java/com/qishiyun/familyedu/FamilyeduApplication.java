package com.qishiyun.familyedu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.qishiyun.familyedu.mapper")
public class FamilyeduApplication {

    public static void main(String[] args) {
        SpringApplication.run(FamilyeduApplication.class, args);
    }

}
