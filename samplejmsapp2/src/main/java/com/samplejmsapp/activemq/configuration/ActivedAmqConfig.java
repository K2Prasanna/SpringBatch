package com.samplejmsapp.activemq.configuration;

import org.apache.activemq.ActiveMQConnectionFactory;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
 
@Configuration
public class ActivedAmqConfig {
 
    public static final String CTS_QUEUE = "cts.queue";
    private static String subject = "VALLYSOFTQ"; //Queue Name
   
    public static Connection createConnection() throws JMSException
    {
    	ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("failover:(http://0.0.0.0:8161,tcp://localhost:61616)?randomize=false");
    	Connection connection = connectionFactory.createConnection();
    	connection.start();
    	return connection;
    }
    
    @Bean
    public Destination activeMQJMSQueue() throws Exception {
    	
    	Session session = createConnection().createSession(false,
    			Session.AUTO_ACKNOWLEDGE);
    			// Destination represents here our queue 'VALLYSOFTQ' on the
    			// JMS server. You don't have to do anything special on the
    			// server to create it, it will be created automatically.
    			Destination destination = session.createQueue(subject);
    			// MessageProducer is used for sending messages (as opposed
    			// to MessageConsumer which is used for receiving them)
    			MessageProducer producer = session.createProducer(destination);
    			// We will send a small text message saying 'Hello' in Japanese
    			TextMessage message = session.createTextMessage("Hello welcome come to vallysoft ActiveMQ!"+System.currentTimeMillis());
    			// Here we are sending the message!
    			producer.send(message);
    			System.out.println("Sentage '" + message.getText() + "'");
        return destination;
    }
}
