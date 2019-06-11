package com.customer.controller;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.customer.exception.CustomerException;
import com.customer.log.LogConst;
import com.customer.log.LoggerSingleton;
import com.customer.model.Customer;
import com.customer.service.CustomerService;

@RestController
@RequestMapping(value = "customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	private static final String CLASSNAME = "CustomerController";
	
	@RequestMapping(value = "getAllCustomers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
	public List<Customer> getAllCustomers(){
		LoggerSingleton.log(LogConst.INFO, CLASSNAME, "getAllCustomers", "Inside getAllCustomers");
		
		List<Customer> list = null;
		try {
			list = customerService.getAllCustomers();
		} catch (CustomerException e) {
			LoggerSingleton.error(CLASSNAME, "getAllCustomers", "Exception occured!", e);
		}
		
		return list;
	}
	
	@RequestMapping(value = "addCustomer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE )
	public void addCustomer(Customer customer) {
		LoggerSingleton.log(LogConst.INFO, CLASSNAME, "addCustomer", "Inside addCustomer");
		
		try {
			customerService.addCustomer(customer);
			rabbitTemplate.convertAndSend(customer);
		} catch (CustomerException e) {
			LoggerSingleton.error(CLASSNAME, "addCustomer", "Exception occured!", e);
		}
	}
	
	@RequestMapping(value = "updateCustomer", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Customer updateCustomer(Customer customer) {
		LoggerSingleton.log(LogConst.INFO, CLASSNAME, "updateCustomer", "Inside updateCustomer");
		
		Customer retCustomer = null;
		try {
			retCustomer = customerService.updateCustomer(customer);
		} catch (CustomerException e) {
			LoggerSingleton.error(CLASSNAME, "updateCustomer", "Exception occured!", e);
		}
		
		return retCustomer;
	}
	
	@RequestMapping(value = "customersById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
	public Customer customersById(long id) {
		LoggerSingleton.log(LogConst.INFO, CLASSNAME, "customersById", "Inside customersById");
		
		Customer retCustomer = null;
		try {
			retCustomer = customerService.getCustomer(id);
		} catch (CustomerException e) {
			LoggerSingleton.error(CLASSNAME, "customersById", "Exception occured!", e);
		}
		
		return retCustomer;
	}
	
	@RequestMapping(value = "deleteCustomer", method = RequestMethod.DELETE)
	public void deleteCustomer(long id) {
		LoggerSingleton.log(LogConst.INFO, CLASSNAME, "deleteCustomer", "Inside deleteCustomer");
		
		try {
			customerService.deleteCustomer(id);
		} catch (CustomerException e) {
			LoggerSingleton.error(CLASSNAME, "deleteCustomer", "Exception occured!", e);
		}
	}
}
