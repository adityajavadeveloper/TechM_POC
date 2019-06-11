package com.item.service;

import java.util.List;

import com.item.exception.ItemException;
import com.item.model.Item;

public interface ItemService {

	public List<Item> getAllItems() throws ItemException;
	public Item getItem(String name) throws ItemException;
	public Item addItem(Item item) throws ItemException;
	public Item updateItem(Item item) throws ItemException;
	public void deleteItem(long id) throws ItemException;
}
