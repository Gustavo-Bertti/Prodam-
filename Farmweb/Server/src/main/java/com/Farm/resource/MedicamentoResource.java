package com.Farm.resource;



import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.Farm.BO.MedicamentoBO;
import com.Farm.DTO.MedicamentoDTO;
import com.Farm.beans.Medicamento;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/medicamento")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class MedicamentoResource {
	private MedicamentoBO medicamentoBO = new MedicamentoBO();
	
	@PostMapping
	public ResponseEntity<Medicamento> create(@RequestBody @Valid MedicamentoDTO medicamentoDTO) throws Exception{
		Medicamento medicamento = new Medicamento();
		medicamento.setNome(medicamentoDTO.getNome());
		medicamento.setDosagem(medicamentoDTO.getDosagem());
		medicamento.setForma(medicamentoDTO.getForma());
		medicamento.setFabricante(medicamentoDTO.getFabricante());
		medicamento.setData(medicamentoDTO.getData());
		medicamento.setIdFornecedor(medicamentoDTO.getIdCategoria());
		medicamento.setIdCategoria(medicamentoDTO.getIdFornecedor());
		
		
		medicamentoBO.cadastrar(medicamento);
		return ResponseEntity.status(HttpStatus.CREATED).body(medicamento);
		
	
	}
	@GetMapping
	public List<Medicamento> getObjects() throws Exception {
		return medicamentoBO.select();

	}
	@GetMapping("{id}")
    public ResponseEntity<Medicamento> getNasPorId(@PathVariable long id) throws Exception{
        return ResponseEntity.of(medicamentoBO.MedicamentoPorId(id));
    }
	
	@DeleteMapping("delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable long id) throws Exception{
        var optional  = medicamentoBO.MedicamentoPorId(id);

        if(optional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

        medicamentoBO.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
	@PutMapping("{id}")
    public ResponseEntity<Object> atualizar(@PathVariable long id, @RequestBody @Valid Medicamento medicamento) throws Exception{
        var optional = medicamentoBO.MedicamentoPorId(id);

        if(optional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

        medicamentoBO.atualizar(medicamento);
        return ResponseEntity.ok(medicamento);
    }
}
