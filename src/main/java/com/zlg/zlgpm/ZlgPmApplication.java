package com.zlg.zlgpm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAsync
@SpringBootApplication
@MapperScan("com.zlg.zlgpm.dao")
public class ZlgPmApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZlgPmApplication.class, args);
    }

}
