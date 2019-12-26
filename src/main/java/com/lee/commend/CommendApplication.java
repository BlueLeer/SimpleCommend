package com.lee.commend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author lee
 * @date 2019/12/26 23:41
 */
@SpringBootApplication
@EnableCaching
public class CommendApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommendApplication.class, args);
    }
}
