package com.sales.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sales.model.Order;

@Repository
public interface OrderDao extends CrudRepository<Order, Long>{

}
