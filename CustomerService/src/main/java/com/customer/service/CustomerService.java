package com.customer.service;

import java.util.List;

import com.customer.exception.CustomerException;
import com.customer.model.Customer;

public interface CustomerService {

	
	public List<Customer> getAllCustomers() throws CustomerException;
	public Customer getCustomer(long id) throws CustomerException;
	public Customer addCustomer(Customer customer) throws CustomerException;
	public Customer updateCustomer(Customer customer) throws CustomerException;
	public void deleteCustomer(long id) throws CustomerException;
	
	
}
