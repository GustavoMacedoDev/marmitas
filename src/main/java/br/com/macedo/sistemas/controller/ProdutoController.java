package br.com.macedo.sistemas.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.macedo.sistemas.domain.Produto;
import br.com.macedo.sistemas.dto.ProdutoNewDto;
import br.com.macedo.sistemas.repository.ProdutoRepository;
import br.com.macedo.sistemas.services.ProdutoService;
@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@RequestMapping(value = "/produtos", method = RequestMethod.GET)
	public @ResponseBody List<Produto> getProdutos() {
		
		return this.produtoRepository.findAll();
		
	}
	
	@RequestMapping(value = "/produto/{id}", method = RequestMethod.GET)
	public @ResponseBody List<Produto> findByCategoria(@PathVariable Integer id) {
		
		System.out.println(id);
		
		List<Produto> produtos = produtoService.findByCategoria(id);
		
		return produtos;
	}
	
	
	@PostMapping(value = "/produto")
	public ResponseEntity<Void> insert(@Valid @RequestBody ProdutoNewDto produtoNewDto) {

			
		 Produto produto = produtoService.insert(produtoNewDto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(produto.getId()).toUri();
		 ResponseEntity.created(uri).build();
		
		return ResponseEntity.created(uri).build();
	}
	

}
