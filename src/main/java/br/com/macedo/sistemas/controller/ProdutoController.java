package br.com.macedo.sistemas.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
import br.com.macedo.sistemas.services.ProdutoService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ProdutoController {
	
	
	@Autowired
	private ProdutoService produtoService;
	 
	@RequestMapping(value = "/produtosativos", method = RequestMethod.GET)
	public @ResponseBody List<Produto> getProdutosAtivos() {
		
		return this.produtoService.findAllAtivos(0);
		
	}
	
	@RequestMapping(value = "/produtosquiosque", method = RequestMethod.GET)
	public @ResponseBody List<Produto> getProdutosQuiosque() {
		
		return this.produtoService.getProdutosQuiosque();
		
	}
	
	@RequestMapping(value = "/produtosentrega", method = RequestMethod.GET)
	public @ResponseBody List<Produto> getProdutosEntrega() {
		
		return this.produtoService.getProdutosEntrega();
		
	}
	
	@RequestMapping(value = "/produtos", method = RequestMethod.GET)
	public @ResponseBody List<Produto> getProdutos() {
		
		return this.produtoService.findAll();
		
	}
	
	
	@RequestMapping(value = "/produto/{id}", method = RequestMethod.GET)
	public @ResponseBody List<Produto> findByCategoria(@PathVariable Integer id) {
		
		List<Produto> produtos = produtoService.findByCategoria(id);
		
		return produtos;
	}
	
	@RequestMapping(value = "/produtoid/{id}", method = RequestMethod.GET)
	public @ResponseBody Produto findByIdProduto(@PathVariable Integer id) {
		
		Produto produto = produtoService.find(id);
		
		return produto;
	}
	
	
	@PostMapping(value = "/produto")
	public ResponseEntity<Void> insert(@Validated @RequestBody ProdutoNewDto produtoNewDto) {

			
		 Produto produto = produtoService.insert(produtoNewDto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(produto.getId()).toUri();
		 ResponseEntity.created(uri).build();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/produto", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Validated @RequestBody Produto produto) {
		
		produto = this.produtoService.update(produto);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/produtoinativa/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> inativaProduto(@PathVariable Integer id) {
		
		this.produtoService.inativa(id);
		
			
		return ResponseEntity.noContent().build();
	}
}












