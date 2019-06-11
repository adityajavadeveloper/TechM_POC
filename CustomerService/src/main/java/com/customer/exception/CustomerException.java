package com.customer.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CustomerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomerException(String s){
		super(s);
	}
	
}
