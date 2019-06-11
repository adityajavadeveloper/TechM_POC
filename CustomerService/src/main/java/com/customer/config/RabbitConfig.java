package com.customer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
	
	@Value("${app.exchange.name}")
	private String exchangeName;
	
	@Value("${app.queue.name}")
	private String queueName;
	
	@Value("${app.routing.key}")
	private String routingKey;
	
	@Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
	
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
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        template.setExchange(exchangeName);
        template.setRoutingKey(routingKey);
        return template;
    }
	
}
