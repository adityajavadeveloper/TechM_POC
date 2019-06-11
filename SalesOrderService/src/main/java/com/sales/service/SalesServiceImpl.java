package com.sales.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sales.dao.CustomerSOSDao;
import com.sales.dao.OrderDao;
import com.sales.exception.CustomerException;
import com.sales.exception.SalesException;
import com.sales.log.LogConst;
import com.sales.log.LoggerSingleton;
import com.sales.model.CustomerOrder;
import com.sales.model.CustomerSOS;
import com.sales.model.Item;
import com.sales.model.Order;
import com.sales.model.ResponseBean;
import com.sales.proxy.ItemServiceProxy;
import com.sales.utils.Utils;

@Service
public class SalesServiceImpl implements SalesService {

	@Autowired
	private CustomerSOSDao customerSOSDao;
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private ItemServiceProxy itemServiceProxy;
	
	private static final String CLASSNAME = "SalesServiceImpl";
	
	@Override
	public CustomerSOS addCustomerSOS(CustomerSOS customerSOS) throws SalesException {
		return customerSOSDao.save(customerSOS);
	}

	@Override
	public ResponseBean orders(CustomerOrder customerOrder) throws Throwable {
		boolean validItem = true;
		
		CustomerException customerException = null;
		Order order = null;
		ResponseBean responseBean = new ResponseBean();
		
		List<Item> itemList = new ArrayList<>();

		CustomerSOS orElseThrow = customerSOSDao.findById(customerOrder.getCustId()).orElseThrow(() -> new CustomerException("Couldn't find a Customer with id: " + customerOrder.getCustId()));
		LoggerSingleton.log(LogConst.INFO, CLASSNAME, "orders", "orElseThrow = " + orElseThrow);
		
		
		for(String item : customerOrder.getItemList()) {
			Item itemByName = itemServiceProxy.itemByName(item);
			System.out.println("itemByName = " + itemByName);
			
			if(itemByName == null) {
				validItem = false;
				customerException = new CustomerException("Couldn't find Item with name: " + item);
				break;
			}else {
				itemList.add(itemByName);
			}
		}
		
		if(validItem) {
			order = new Order();
			order.setCustId(customerOrder.getCustId());
			order.setOrderDate(customerOrder.getOrderDate());
			order.setOrderDesc(customerOrder.getOrderDesc());
			
			order.setTotalPrice(Utils.setTotalPrice(itemList));
			order.setOrderLineItem(Utils.setOrderLineItem(itemList, order));
			
			LoggerSingleton.log(LogConst.INFO, CLASSNAME, "orders", "Finally order = " + order);
			
		}else {
			throw customerException;
		}
		
		order = orderDao.save(order);
		responseBean.setOrderId(order.getOrderId());
		
		return responseBean;
	}

}
