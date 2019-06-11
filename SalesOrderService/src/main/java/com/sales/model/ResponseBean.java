package com.sales.model;

import com.sales.constant.SalesConstant;

import lombok.Data;

@Data
public class ResponseBean {

	private long orderId;
	private String respCode;
	private String respDesc;
	
	public ResponseBean() {
		this.respCode = SalesConstant.SUCCESS;
		this.respDesc = SalesConstant.SUCCESS_DESC;
	}
	
}
