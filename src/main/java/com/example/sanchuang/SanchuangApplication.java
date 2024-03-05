package com.example.sanchuang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.*;

@SpringBootApplication
@MapperScan("com.example.sanchuang.mapper")
public class SanchuangApplication {

    public static void main(String[] args) {
        SpringApplication.run(SanchuangApplication.class, args);
        System.out.println("=====启动成功=====");
    }
}
