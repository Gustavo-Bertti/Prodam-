package com.Farm.BO;

import java.io.IOException;
import java.sql.SQLException;
import com.Farm.DAO.FornecedorDAO;
import com.Farm.beans.Fornecedor;
import com.Farm.conexao.DaoFactory;
import com.Farm.exception.FornecedorBOException;

public class FornecedorBO {
private FornecedorDAO forn;
	
	public FornecedorBO() {
		try {
			forn = DaoFactory.getFornecedorDAO();
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void cadastrar (Fornecedor fornecedor) throws FornecedorBOException{
		try {
			if (fornecedor.getId() == 0 && !fornecedor.getNome().equals("")) {
				forn.insert(fornecedor);			
			} else {
				throw new FornecedorBOException();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	public long selectIdFornecedorePorNomeETelefoneEEndereco(String nome, String telefone, String endereco) throws SQLException{
		return forn.selectIdFornecedorePorNomeETelefoneEEndereco(nome, telefone, endereco);
	}
}
