package com.sales.service;

import com.sales.exception.SalesException;
import com.sales.model.CustomerOrder;
import com.sales.model.CustomerSOS;
import com.sales.model.ResponseBean;

public interface SalesService {

	public CustomerSOS addCustomerSOS(CustomerSOS customerSOS) throws SalesException;
	public ResponseBean orders(CustomerOrder customerOrder) throws Throwable;
	
}
