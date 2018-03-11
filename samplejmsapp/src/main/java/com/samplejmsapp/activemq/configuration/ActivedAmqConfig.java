package com.samplejmsapp.activemq.configuration;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 
import javax.jms.Queue;
 
@Configuration
public class ActivedAmqConfig {
 
    public static final String CTS_QUEUE = "cts.queue";
 
    @Bean
    public Queue activeMQJMSQueue() {
        return new ActiveMQQueue(CTS_QUEUE);
    }
}
