package com.sales.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sales.config.ApplicationContextProvider;
import com.sales.log.LogConst;
import com.sales.log.LoggerSingleton;
import com.sales.model.CustomerSOS;

@Component
public class RabbitMessageListener implements MessageListener {
	
	private SalesService salesService;
	
	private static final String CLASSNAME = "SalesServiceImpl";
	
	@Override
	public void onMessage(Message message) {
		salesService = ApplicationContextProvider.getApplicationContext().getBean(SalesServiceImpl.class);
		
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			CustomerSOS customerSOS = objectMapper.readValue(new String(message.getBody()), CustomerSOS.class);
			LoggerSingleton.log(LogConst.INFO, CLASSNAME, "orders", "After convert customerSOS = " + customerSOS);
			
			salesService.addCustomerSOS(customerSOS);
			
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
}
