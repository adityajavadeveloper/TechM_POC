package com.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.dao.CustomerDao;
import com.customer.exception.CustomerException;
import com.customer.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;
	
	@Override
	public List<Customer> getAllCustomers() throws CustomerException {
		return (List<Customer>) customerDao.findAll();
	}

	@Override
	public Customer getCustomer(long id) throws CustomerException {
		return customerDao.findById(id).orElseThrow(() -> new CustomerException("Couldn't find a Customer with id: " + id));
	}

	@Override
	public Customer addCustomer(Customer customer) throws CustomerException {
		return customerDao.save(customer);
	}

	@Override
	public Customer updateCustomer(Customer customer) throws CustomerException {
		return customerDao.save(customer);
	}

	@Override
	public void deleteCustomer(long id) throws CustomerException{
		customerDao.deleteById(id);
	}

}
