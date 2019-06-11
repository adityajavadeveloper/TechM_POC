package com.sales.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="order_line_item")
@Getter
@Setter
public class OrderLineItem {

	@Id
    @GeneratedValue
    @Column(name="id")
	private long id;
	
	@Column(name="item_name")
	private String itemName;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "orderId")
    private Order order;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderLineItem other = (OrderLineItem) obj;
		if (id != other.id)
			return false;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		return true;
	}
	
	
	
}
