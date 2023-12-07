package com.Farm.resource;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Farm.BO.FornecedorBO;
import com.Farm.DTO.FornecedorDTO;
import com.Farm.beans.Fornecedor;




@RestController
@RequestMapping(value = "/fornecedor")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class FornecedorResource {
	private FornecedorBO fornecedorBO = new FornecedorBO();
	
	@PostMapping
	public ResponseEntity<Fornecedor> create(@RequestBody @Valid FornecedorDTO fornecedorDTO) throws Exception{
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setNome(fornecedorDTO.getNome());
		fornecedor.setTelefone(fornecedorDTO.getTelefone());
		fornecedor.setEndereco(fornecedorDTO.getEndereco());
		
		
		fornecedorBO.cadastrar(fornecedor);
		return ResponseEntity.status(HttpStatus.CREATED).body(fornecedor);
		
	
	}
	@GetMapping("/{nome}-{telefone}-{endereco}")
	public ResponseEntity<Long> getIdFornecedorePorNomeETelefoneEEndereco(
	    @PathVariable String nome,
	    @PathVariable String telefone,
	    @PathVariable String endereco) throws Exception {

	    long idFornecedor = fornecedorBO.selectIdFornecedorePorNomeETelefoneEEndereco(nome, telefone, endereco);
	    return ResponseEntity.ok(idFornecedor);
	}

}
