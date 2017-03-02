package com.jms.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.jms.adapter.ConsumerAdapter;

@Component
public class ConsumerListener implements MessageListener
{
	private static Logger logger	=	LogManager.getLogger(ConsumerListener.class.getName());
	
	@Autowired
	JmsTemplate	jmstemplate;
	
	@Autowired
	ConsumerAdapter	consumerAdapter;

	@Autowired(required = false)
	public void onMessage(Message message)
	{
		logger.info("in OnMessage  -  This from  Logger.info");
	
		String	json	=	null;
		
		if (message instanceof TextMessage)
		{
			try
			{
				json	=	((TextMessage)message).getText();
				logger.info("sending JSON to DB - " + json);
				consumerAdapter.sendToMongo(json);
			} 
			catch (JMSException e)
			{
				jmstemplate.convertAndSend(json);
				logger.error("message: " + json);
			}
		}
	}

}
