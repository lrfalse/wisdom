package com.wisdom.utils;

public class MyRuntimeException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public MyRuntimeException(Throwable e){
		super(e);
	}

	public MyRuntimeException(){}
	
	public MyRuntimeException(String error){}
}
