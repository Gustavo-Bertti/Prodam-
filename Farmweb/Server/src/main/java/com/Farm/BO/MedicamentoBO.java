package com.Farm.BO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import com.Farm.DAO.MedicamentoDAO;
import com.Farm.beans.Medicamento;
import com.Farm.conexao.DaoFactory;
import com.Farm.exception.MedicamentoBOException;

public class MedicamentoBO {
	private MedicamentoDAO  med;
	
	public MedicamentoBO() {
		try {
			med = DaoFactory.getMedicamentoDAO();
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void cadastrar (Medicamento medicamento) throws MedicamentoBOException{
		try {
			if (medicamento.getId() == 0 && !medicamento.getNome().equals("")) {
				med.insert(medicamento);			
			} else {
				throw new MedicamentoBOException();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	public List<Medicamento> select() throws SQLException {
		return med.selectMedicamento();
	}
	
	public Optional<Medicamento> MedicamentoPorId(long id) throws Exception {
		Optional<Medicamento> medicamento = med.selectNasPorId(id);
		return medicamento;
	}
	public void delete(long id)throws Exception {
		med.deleteMedicamento(id);
	}
	public void atualizar(Medicamento medicamento) throws SQLException {
		med.atualizar(medicamento);
	}
}
