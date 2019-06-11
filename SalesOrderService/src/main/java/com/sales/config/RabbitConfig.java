package com.sales.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sales.service.RabbitMessageListener;

@Configuration
public class RabbitConfig {
	
	@Autowired
	private ConnectionFactory conFac;
	
	@Value("${app.exchange.name}")
	private String exchangeName;
	
	@Value("${app.queue.name}")
	private String queueName;
	
	@Value("${app.routing.key}")
	private String routingKey;
	
	@Bean
	public DirectExchange directExchange() {
		return new DirectExchange(exchangeName, true, false);
	}
	
	@Bean
	public Queue queue() {
		return new Queue(queueName, true, false, false);
	}
	
	@Bean
    Binding binding() {
        return BindingBuilder.bind(queue()).to(directExchange()).with(routingKey);
    }
	
	@Bean
    SimpleMessageListenerContainer container() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(conFac);
        container.setQueueNames(queueName);
        container.setMessageListener(new RabbitMessageListener());
        return container;
    }
	
}
