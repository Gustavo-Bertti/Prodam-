package com.Farm.beans;

public class Medicamento {
	private long id;
	private String nome;
	private String dosagem;
	private String forma;
	private String fabricante;
	private String data;
	private long idFornecedor;
	private long idCategoria;
	
	

	/**
	 * 
	 */
	public Medicamento() {
		
	}
	/**
	 * @param id
	 * @param nome
	 * @param dosagem
	 * @param forma
	 * @param fabricante
	 * @param data
	 * @param idFornecedor
	 * @param idCategoria
	 */
	public Medicamento(long id, String nome, String dosagem, String forma, String fabricante, String data,
			long idFornecedor, long idCategoria) {
		this.id = id;
		this.nome = nome;
		this.dosagem = dosagem;
		this.forma = forma;
		this.fabricante = fabricante;
		this.data = data;
		this.setIdFornecedor(idFornecedor);
		this.setIdCategoria(idCategoria);
	}
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return the dosagem
	 */
	public String getDosagem() {
		return dosagem;
	}
	/**
	 * @param dosagem the dosagem to set
	 */
	public void setDosagem(String dosagem) {
		this.dosagem = dosagem;
	}
	/**
	 * @return the forma
	 */
	public String getForma() {
		return forma;
	}
	/**
	 * @param forma the forma to set
	 */
	public void setForma(String forma) {
		this.forma = forma;
	}
	/**
	 * @return the fabricante
	 */
	public String getFabricante() {
		return fabricante;
	}
	/**
	 * @param fabricante the fabricante to set
	 */
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}
	public long getIdFornecedor() {
		return idFornecedor;
	}
	public void setIdFornecedor(long idFornecedor) {
		this.idFornecedor = idFornecedor;
	}
	public long getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(long idCategoria) {
		this.idCategoria = idCategoria;
	}
}
