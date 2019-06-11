package com.sales.constant;

public class SalesConstant {

	public static final long DEFAULT_ORDER_ID = -1;
	
	
	public static final String SUCCESS = "TM00";
	public static final String SUCCESS_DESC = "Order Accepted";
	
	public static final String CUST_NOT_FOUND = "TM01";
	public static final String CUST_NOT_FOUND_DESC = "Couldn't find a Customer with id:";
	
	public static final String ITEM_NOT_FOUND = "TM02";
	public static final String ITEM_NOT_FOUND_DESC = "Couldn't find Item with name:";
	
	public static final String INTERNAL_SERVER_ERROR = "TM03";
	public static final String INTERNAL_SERVER_ERROR_DESC = "Couldn't process your order. Please try again.";
}
