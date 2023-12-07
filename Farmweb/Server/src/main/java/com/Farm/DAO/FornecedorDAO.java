package com.Farm.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Farm.Singleton.ConnectionManager;
import com.Farm.beans.Fornecedor;


public class FornecedorDAO {

private Connection connection;
	
	public FornecedorDAO(Connection connection){
		try {
			ConnectionManager cm = ConnectionManager.getInstance();
	    	connection = cm.getConnection();
	    	this.connection = connection;
		} catch (Exception e) {
			e.printStackTrace();
		}		
    }  
	
	public void insert(Fornecedor fornecedor) throws SQLException{   
        try {        
            String sql = "INSERT INTO FORNECEDOR VALUES (SEQ_FORNECEDOR.NEXTVAL, ?, ?, ?)";   
            PreparedStatement stmt = connection.prepareStatement(sql);   
            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getTelefone());
            stmt.setString(3, fornecedor.getEndereco());
            
            stmt.execute();   
            stmt.close();   
        } catch (SQLException e) {   
            e.printStackTrace();   
        }    
    }
	
	public long selectIdFornecedorePorNomeETelefoneEEndereco(String nome, String telefone, String endereco) throws SQLException {
		 try {
		        long id = 0;
		        String sql1 = "SELECT * FROM Fornecedor WHERE Nome = ? AND Contato = ? AND Endereco = ?";
		        
		        try (PreparedStatement st = connection.prepareStatement(sql1)) {
		            st.setString(1, nome);
		            st.setString(2, telefone);
		            st.setString(3, endereco);

		            try (ResultSet rs = st.executeQuery()) {
		                while (rs.next()) {
		                    id = rs.getLong("ID");
		                }
		            }
		        }

		        return id;
		    } catch (Exception e) {
		        e.printStackTrace();  // Considere usar um logger para registrar o erro de forma adequada
		        throw new SQLException("Erro ao obter ID do fornecedor.", e);
		    }}
}
