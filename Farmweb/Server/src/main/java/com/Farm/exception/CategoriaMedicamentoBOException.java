package com.Farm.exception;

public class CategoriaMedicamentoBOException extends Exception {
	public CategoriaMedicamentoBOException() {
		super("Não foi informado o nome ou ID é 0");
	}	
	
	public CategoriaMedicamentoBOException(String msg) {
		super(msg);
	}
}
