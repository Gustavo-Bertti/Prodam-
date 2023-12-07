package com.Farm.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.Farm.Singleton.ConnectionManager;
import com.Farm.beans.Medicamento;

public class MedicamentoDAO {
private Connection connection;
	
	public MedicamentoDAO(Connection connection){
		try {
			ConnectionManager cm = ConnectionManager.getInstance();
	    	connection = cm.getConnection();
	    	this.connection = connection;
		} catch (Exception e) {
			e.printStackTrace();
		}		
    }  
	
	public void insert(Medicamento medicamento) throws SQLException{   
        try {        
            String sql = "INSERT INTO MEDICAMENTO VALUES (SEQ_MEDICAMENTO.NEXTVAL, ?, ?, ?, ?,TO_DATE(?, 'DD/MM/YYYY'), ?, ?)";   
            PreparedStatement stmt = connection.prepareStatement(sql);   
            stmt.setString(1, medicamento.getNome());
            stmt.setString(2, medicamento.getDosagem());
            stmt.setString(3, medicamento.getForma());
            stmt.setString(4, medicamento.getFabricante());
            stmt.setString(5, medicamento.getData());
            stmt.setLong(6, medicamento.getIdFornecedor());
            stmt.setLong(7, medicamento.getIdCategoria());
            
            stmt.execute();   
            stmt.close();   
        } catch (SQLException e) {   
            e.printStackTrace();   
        }    
    }
	public List<Medicamento> selectMedicamento() throws SQLException {
		try {
			List<Medicamento> listaMedicamento= new ArrayList<>();
			long id = 0;
			String nome = "";
			String dosagem ="";
			String forma = "";
			String fabricante = "";
			String data = "";
			long  idFornecedor = 0;
			long  idCategoria= 0;
			String sql = "SELECT * FROM MEDICAMENTO";
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				id = rs.getLong("ID");
				nome = rs.getString("NOME");
				dosagem = rs.getString("DOSAGEM");
				forma = rs.getString("FORMAFARMACEUTICA");
				fabricante = rs.getString("FABRICANTE");
				data = rs.getString("DATAVALIDADE");
				idFornecedor = rs.getLong("ID_FORNECEDOR");
				idCategoria = rs.getLong("ID_CATEGORIA");
				Medicamento medicamento = new Medicamento(id, nome, dosagem, forma, fabricante, data, idFornecedor, idCategoria);
				listaMedicamento.add(medicamento);
			}
			return listaMedicamento;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public Optional<Medicamento> selectNasPorId(long vId) throws SQLException {
		try {
			Medicamento medicamento = new Medicamento();
			long id = 0;
			String nome = "";
			String dosagem ="";
			String forma = "";
			String fabricante = "";
			String data = "";
			long  idFornecedor = 0;
			long  idCategoria= 0;
			String sql = "SELECT * FROM MEDICAMENTO WHERE ID = " + vId;
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				id = rs.getLong("ID");
				nome = rs.getString("NOME");
				dosagem = rs.getString("DOSAGEM");
				forma = rs.getString("FORMAFARMACEUTICA");
				fabricante = rs.getString("FABRICANTE");
				data = rs.getString("DATAVALIDADE");
				idFornecedor = rs.getLong("ID_FORNECEDOR");
				idCategoria = rs.getLong("ID_CATEGORIA");
				medicamento = new Medicamento(id, nome, dosagem, forma, fabricante, data, idFornecedor, idCategoria);
	            
			}
			return Optional.of(medicamento);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void deleteMedicamento(long id) throws SQLException {
	    try {
	        String sql = "DELETE FROM MEDICAMENTO WHERE ID = " + id;
	        Statement st = connection.createStatement();
	        st.executeUpdate(sql);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public void atualizar(Medicamento medicamento) throws SQLException {
		String sql = "UPDATE MEDICAMENTO SET NOME = ?, DOSAGEM = ?, FORMAFARMACEUTICA = ?, FABRICANTE = ?, DATAVALIDADE = TO_DATE(?, 'DD/MM/YYYY') WHERE ID = ?";		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, medicamento.getNome());
			ps.setString(2, medicamento.getDosagem());
			ps.setString(3, medicamento.getForma());
			ps.setString(4, medicamento.getFabricante());
			ps.setString(5, medicamento.getData());
			ps.setLong(6, medicamento.getId());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();

		} 
	}
}
