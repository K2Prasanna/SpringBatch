package com.samplejmsapp.activemq.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.stereotype.Service;

import com.samplejmsapp.activemq.configuration.ActivedAmqConfig;

@Service
public class ActiveAmqService {
	public void processMsg(String msg) throws JMSException {
		DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println("*************"+ msg + " as of "+sdf.format(date)+" ***********  " );
		
		
		// MessageConsumer is used for receiving (consuming) messages
		/*Session session = ActivedAmqConfig.createConnection().createSession(false,
    			Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue("VALLYSOFTQ");
		MessageConsumer consumer = session.createConsumer(destination);

		// Here we receive the message.
		// By default this call is blocking, which means it will wait
		// for a message to arrive on the queue.
		Message message = consumer.receive();

		// There are many types of Message and TextMessage
		// is just one of them. Producer sent us a TextMessage
		// so we must cast to it to get access to its .getText()
		// method.
		if (message instanceof TextMessage) {
		TextMessage textMessage = (TextMessage) message;
		System.out.println("Receivedage '" + textMessage.getText()
		+ "'");
		
		
	 
	}*/
	}

}
