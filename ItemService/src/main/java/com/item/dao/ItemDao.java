package com.item.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.item.model.Item;

@Repository
public interface ItemDao extends CrudRepository<Item, Long> {

	Item findByName(String name);
	
}
