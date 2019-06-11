package com.sales.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="sales_order")
@Getter
@Setter
public class Order {

	@Id
    @GeneratedValue
    @Column(name="orderId")
	private long orderId;
	
	@Column(name="order_date")
	private Date orderDate;
	
	@Column(name="cust_id")
	private long custId;
	
	@Column(name="order_desc")
	private String orderDesc;
	
	@Column(name="total_price")
	private long totalPrice;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="order", cascade = CascadeType.ALL)
    private List<OrderLineItem> orderLineItem = new ArrayList<>();

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (custId != other.custId)
			return false;
		if (orderDate == null) {
			if (other.orderDate != null)
				return false;
		} else if (!orderDate.equals(other.orderDate))
			return false;
		if (orderDesc == null) {
			if (other.orderDesc != null)
				return false;
		} else if (!orderDesc.equals(other.orderDesc))
			return false;
		if (orderId != other.orderId)
			return false;
		if (orderLineItem == null) {
			if (other.orderLineItem != null)
				return false;
		} else if (!orderLineItem.equals(other.orderLineItem))
			return false;
		if (totalPrice != other.totalPrice)
			return false;
		return true;
	}
	
	
	
}
