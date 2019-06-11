package com.sales.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sales.constant.SalesConstant;
import com.sales.exception.CustomerException;
import com.sales.log.LogConst;
import com.sales.log.LoggerSingleton;
import com.sales.model.CustomerOrder;
import com.sales.model.ResponseBean;
import com.sales.service.SalesService;

@RestController
@RequestMapping(value = "order")
public class OrderController {
	
	@Autowired
	private SalesService salesService;
	
	private static final String CLASSNAME = "OrderController";

	@HystrixCommand(fallbackMethod = "orderFallBack")
	@RequestMapping(value = "createOrder", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseBean orders(CustomerOrder customerOrder) throws Throwable {
		LoggerSingleton.log(LogConst.INFO, CLASSNAME, "orders", "customerOrder = " + customerOrder);
		ResponseBean responseBean = null;
		
		try {
			responseBean = salesService.orders(customerOrder);
		} catch (Throwable e) {
			e.printStackTrace();
			throw e;
		}
		
		LoggerSingleton.log(LogConst.INFO, CLASSNAME, "orders", "finally responseBean = " + responseBean);
		return responseBean;
	}
	
	public ResponseBean orderFallBack(CustomerOrder customerOrder, Throwable e) {
		LoggerSingleton.log(LogConst.INFO, CLASSNAME, "orderFallBack", "inside fallBack method");
		ResponseBean responseBean = new ResponseBean();
		
		if(e instanceof CustomerException) {
			if(e.getMessage().contains(SalesConstant.CUST_NOT_FOUND_DESC)) {
				responseBean.setOrderId(SalesConstant.DEFAULT_ORDER_ID);
				responseBean.setRespCode(SalesConstant.CUST_NOT_FOUND);
				responseBean.setRespDesc(e.getMessage());
			}else if(e.getMessage().contains(SalesConstant.ITEM_NOT_FOUND_DESC)){
				responseBean.setOrderId(SalesConstant.DEFAULT_ORDER_ID);
				responseBean.setRespCode(SalesConstant.ITEM_NOT_FOUND);
				responseBean.setRespDesc(e.getMessage());
			}else {
				responseBean.setOrderId(SalesConstant.DEFAULT_ORDER_ID);
				responseBean.setRespCode(SalesConstant.INTERNAL_SERVER_ERROR);
				responseBean.setRespDesc(SalesConstant.INTERNAL_SERVER_ERROR_DESC);
			}
		}else {
			responseBean.setOrderId(SalesConstant.DEFAULT_ORDER_ID);
			responseBean.setRespCode(SalesConstant.INTERNAL_SERVER_ERROR);
			responseBean.setRespDesc(SalesConstant.INTERNAL_SERVER_ERROR_DESC);
		}
		
		return responseBean;
	}
	
}
