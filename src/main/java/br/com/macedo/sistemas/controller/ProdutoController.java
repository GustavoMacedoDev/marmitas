package br.com.macedo.sistemas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.macedo.sistemas.domain.Produto;
import br.com.macedo.sistemas.repository.ProdutoRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@RequestMapping(value = "/produto", method = RequestMethod.GET)
	public @ResponseBody List<Produto> getProdutos() {
		
		return this.produtoRepository.findAll();
		
	}

}
