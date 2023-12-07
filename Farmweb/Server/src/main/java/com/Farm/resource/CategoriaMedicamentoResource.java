package com.Farm.resource;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.Farm.BO.CategoriaMedicamentoBO;
import com.Farm.DTO.CategoriaMedicamentoDTO;
import com.Farm.beans.CategoriaMedicamento;

import jakarta.validation.Valid;


@RestController
@RequestMapping(value = "/categoria")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class CategoriaMedicamentoResource {
	private CategoriaMedicamentoBO categoriaBO = new CategoriaMedicamentoBO();
	
	@PostMapping
	public ResponseEntity<CategoriaMedicamento> create(@RequestBody @Valid CategoriaMedicamentoDTO categoriaDTO) throws Exception{
		CategoriaMedicamento categoria = new CategoriaMedicamento();
		categoria.setNome(categoriaDTO.getNome());
		categoria.setDescricao(categoriaDTO.getDescricao());
		
		
		
		categoriaBO.cadastrar(categoria);
		return ResponseEntity.status(HttpStatus.CREATED).body(categoria);
		
	
	}
	
	@GetMapping("/{nome}-{descricao}")
	public ResponseEntity<Long> getIdCategoriaePorNomeEDescricao(
	    @PathVariable String nome,
	    @PathVariable String descricao) throws Exception {

	    long idCategoria = categoriaBO.selectCategoriaPorNomeEDescricao(nome, descricao);
	    return ResponseEntity.ok(idCategoria);
	}
}
