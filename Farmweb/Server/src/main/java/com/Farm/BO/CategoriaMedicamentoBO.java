package com.Farm.BO;

import java.io.IOException;
import java.sql.SQLException;

import com.Farm.DAO.CategoriaMedicamentoDAO;
import com.Farm.beans.CategoriaMedicamento;

import com.Farm.conexao.DaoFactory;
import com.Farm.exception.CategoriaMedicamentoBOException;


public class CategoriaMedicamentoBO {
private CategoriaMedicamentoDAO cat;
	
	public CategoriaMedicamentoBO() {
		try {
			cat = DaoFactory.getCategoriaMedicamentoDAO();
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void cadastrar (CategoriaMedicamento categoria) throws CategoriaMedicamentoBOException{
		try {
			if (categoria.getId() == 0 && !categoria.getNome().equals("")) {
				cat.insert(categoria);			
			} else {
				throw new CategoriaMedicamentoBOException();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	public long selectCategoriaPorNomeEDescricao(String nome, String descricao) throws SQLException {
	    return cat.selectIdCategoriaePorNomeEDescricao(nome, descricao);
	}
}
