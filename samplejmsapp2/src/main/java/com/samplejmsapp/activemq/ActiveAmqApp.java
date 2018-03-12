package com.samplejmsapp.activemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
 
@SpringBootApplication
@ImportResource("classpath*:/springintegration-config.xml")
public class ActiveAmqApp {
 
    public static void main(String[] args) {
        SpringApplication.run(ActiveAmqApp.class, args);
    }
}
