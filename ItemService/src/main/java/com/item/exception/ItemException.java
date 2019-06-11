package com.item.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ItemException extends Exception{

	private static final long serialVersionUID = 1L;

	public ItemException(String s){
		super(s);
	}
}
