package com.sales.utils;

import java.util.ArrayList;
import java.util.List;

import com.sales.model.Item;
import com.sales.model.Order;
import com.sales.model.OrderLineItem;

public class Utils {

	public static long setTotalPrice(List<Item> itemList) {
		long totPrice = 0;
		for(Item item : itemList) {
			totPrice += item.getPrice();
		}
		return totPrice;
	}

	public static List<OrderLineItem> setOrderLineItem(List<Item> itemList, Order order) {
		List<OrderLineItem> orderList = new ArrayList<>();
		
		for(Item item : itemList) {
			orderList.add(item.toOrderLineItem(item, order));
		}
		
		return orderList;
	}
	
}
