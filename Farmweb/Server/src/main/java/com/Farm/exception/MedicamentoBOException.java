package com.Farm.exception;

public class MedicamentoBOException extends Exception {
	
	public MedicamentoBOException() {
		super("Não foi informado o nome ou ID é 0");
	}	
	
	public  MedicamentoBOException(String msg) {
		super(msg);
	}
	
}
