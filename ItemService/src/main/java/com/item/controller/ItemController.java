package com.item.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.item.exception.ItemException;
import com.item.log.LogConst;
import com.item.log.LoggerSingleton;
import com.item.model.Item;
import com.item.service.ItemService;

@RestController
@RequestMapping(value = "item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	private static final String CLASSNAME = "ItemController";
	
	@RequestMapping(value = "getAllItems", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
	public List<Item> getAllItems(){
		LoggerSingleton.log(LogConst.INFO, CLASSNAME, "getAllItems", "Inside getAllItems");
		
		List<Item> list = null;
		try {
			list = itemService.getAllItems();
		} catch (ItemException e) {
			LoggerSingleton.error(CLASSNAME, "getAllItems", "Exception occured!", e);
		}
		
		return list;
	}
	
	@RequestMapping(value = "addItem", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE )
	public void addItem(Item item) {
		LoggerSingleton.log(LogConst.INFO, CLASSNAME, "addItem", "Inside addItems item = " + item);
		
		try {
			itemService.addItem(item);
		} catch (ItemException e) {
			LoggerSingleton.error(CLASSNAME, "addItem", "Exception occured!", e);
		}
	}
	
	@RequestMapping(value = "updateItem", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Item updateItem(Item item) {
		LoggerSingleton.log(LogConst.INFO, CLASSNAME, "updateItem", "Inside updateItem item = " + item);
		
		Item retItem = null;
		try {
			retItem = itemService.updateItem(item);
		} catch (ItemException e) {
			LoggerSingleton.error(CLASSNAME, "updateItem", "Exception occured!", e);
		}
		
		return retItem;
	}
	
	@RequestMapping(value = "itemByName", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
	public Item itemByName(String name) {
		LoggerSingleton.log(LogConst.INFO, CLASSNAME, "itemByName", "Inside itemByName itemName = " + name);
		
		Item retItem = null;
		try {
			retItem = itemService.getItem(name);
		} catch (ItemException e) {
			LoggerSingleton.error(CLASSNAME, "itemByName", "Exception occured!", e);
		}
		
		return retItem;
	}
	
	@RequestMapping(value = "deleteItem", method = RequestMethod.DELETE)
	public void deleteItem(long id) {
		LoggerSingleton.log(LogConst.INFO, CLASSNAME, "deleteItem", "Inside deleteItem itemId = " + id);
		
		try {
			itemService.deleteItem(id);
		} catch (ItemException e) {
			LoggerSingleton.error(CLASSNAME, "deleteItem", "Exception occured!", e);
		}
	}

}
