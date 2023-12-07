package com.Farm.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.Farm.Singleton.ConnectionManager;
import com.Farm.beans.CategoriaMedicamento;


public class CategoriaMedicamentoDAO {

private Connection connection;
	
	public CategoriaMedicamentoDAO(Connection connection){
		try {
			ConnectionManager cm = ConnectionManager.getInstance();
	    	connection = cm.getConnection();
	    	this.connection = connection;
		} catch (Exception e) {
			e.printStackTrace();
		}		
    }  
	
	public void insert(CategoriaMedicamento categoria) throws SQLException{   
        try {        
            String sql = "INSERT INTO CATEGORIADEMEDICAMENTO VALUES (SEQ_CATEGORIA_MEDICAMENTO.NEXTVAL, ?, ?)";   
            PreparedStatement stmt = connection.prepareStatement(sql);   
            stmt.setString(1, categoria.getNome());
            stmt.setString(2, categoria.getDescricao());
       
            
            stmt.execute();   
            stmt.close();   
        } catch (SQLException e) {   
            e.printStackTrace();   
        }    
    }
	
	public long selectIdCategoriaePorNomeEDescricao(String nome, String descricao) throws SQLException {
	    try {
	        long id = 0;
	        String sql1 = "SELECT * FROM CategoriaDeMedicamento WHERE Nome = ? AND Descricao = ?";
	        
	        try (PreparedStatement st = connection.prepareStatement(sql1)) {
	            st.setString(1, nome);
	            st.setString(2, descricao);

	            try (ResultSet rs = st.executeQuery()) {
	                while (rs.next()) {
	                    id = rs.getLong("ID");
	                }
	            }
	        }

	        return id;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return 0;
	}
}
