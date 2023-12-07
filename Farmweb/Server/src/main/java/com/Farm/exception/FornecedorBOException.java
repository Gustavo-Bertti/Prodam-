package com.Farm.exception;

public class FornecedorBOException extends Exception {

	public FornecedorBOException() {
		super("Não foi informado o nome ou ID é 0");
	}	
	
	public  FornecedorBOException(String msg) {
		super(msg);
	}
}
