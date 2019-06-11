package com.sales.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SalesException extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SalesException(String s) {
		super(s);
	}
	
}
