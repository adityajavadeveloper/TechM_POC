package com.sales.model;

import lombok.Data;

@Data
public class Item {
    private long id;
	private String name;
	private String description;
	private Long price;
	
	public OrderLineItem toOrderLineItem(Item item, Order order) {
		OrderLineItem orderLineItem = new OrderLineItem();
		orderLineItem.setItemName(item.getName());
		orderLineItem.setOrder(order);
		return orderLineItem;
	}
}
