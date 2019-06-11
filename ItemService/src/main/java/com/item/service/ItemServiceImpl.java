package com.item.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.item.dao.ItemDao;
import com.item.exception.ItemException;
import com.item.model.Item;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemDao itemDao;

	@Override
	public List<Item> getAllItems() throws ItemException {
		return (List<Item>) itemDao.findAll();
		
	}

	@Override
	public Item getItem(String name) throws ItemException {
		return itemDao.findByName(name);
		
	}

	@Override
	public Item addItem(Item item) throws ItemException {
		return itemDao.save(item);
		
	}

	@Override
	public Item updateItem(Item item) throws ItemException {
		return itemDao.save(item);
	}

	@Override
	public void deleteItem(long id) throws ItemException {
		itemDao.deleteById(id);
		
	}

}
