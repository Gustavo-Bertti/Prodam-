package com.Farm.conexao;

import java.io.IOException;
import java.sql.SQLException;

import com.Farm.DAO.CategoriaMedicamentoDAO;
import com.Farm.DAO.FornecedorDAO;
import com.Farm.DAO.MedicamentoDAO;
import com.Farm.Singleton.ConnectionManager;

public class DaoFactory {

	public static MedicamentoDAO getMedicamentoDAO() throws SQLException, IOException{
		return new MedicamentoDAO(ConnectionManager.getInstance().getConnection());
	}
	public static FornecedorDAO getFornecedorDAO()  throws SQLException, IOException{
		return new FornecedorDAO(ConnectionManager.getInstance().getConnection());
	}
	public static CategoriaMedicamentoDAO getCategoriaMedicamentoDAO()  throws SQLException, IOException{
		return new CategoriaMedicamentoDAO(ConnectionManager.getInstance().getConnection());
	}
}
