package com.sales.model;

import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data
public class CustomerOrder {

	private String orderDesc;
	private Date orderDate;
	private long custId;
	private List<String> itemList;
	
}
