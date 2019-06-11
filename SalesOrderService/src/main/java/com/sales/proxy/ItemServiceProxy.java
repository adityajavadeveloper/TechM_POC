package com.sales.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sales.model.Item;

@FeignClient("ItemService")
@RibbonClient("ItemService")
public interface ItemServiceProxy {

	@RequestMapping(value = "itemByName", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
	public Item itemByName(@RequestParam("name") String name);
	
}
