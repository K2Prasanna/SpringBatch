package com.samplejmsapp.activemq;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jms.core.JmsTemplate;
 
@SpringBootApplication
@ImportResource("classpath*:/springintegration-config.xml")
public class ActiveAmqApp {
 
    public static void main(String[] args) {
    	 ConfigurableApplicationContext context = SpringApplication.run(ActiveAmqApp.class, args);
        
    /*  //Get JMS template bean reference
        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
 
        // Send a message
        System.out.println("Sending a message.");
        jmsTemplate.convertAndSend("jms.message.endpoint", new TextMessage(1001L, "test body", new Date())); */
    }
}
