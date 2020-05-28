package br.com.macedo.sistemas.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.macedo.sistemas.domain.Endereco;
import br.com.macedo.sistemas.services.EnderecoService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class EnderecoController {
	
	@Autowired
	private EnderecoService enderecoService;
	
	@RequestMapping(value = "/cadastra-endereco",method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Validated @RequestBody Endereco endereco) {
		
		Endereco end = enderecoService.insert(endereco);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(end.getIdEndereco()).toUri();
		return ResponseEntity.created(uri).build();
	}
	

}
