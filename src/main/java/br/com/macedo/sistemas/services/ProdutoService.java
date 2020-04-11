package br.com.macedo.sistemas.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.macedo.sistemas.domain.Produto;
import br.com.macedo.sistemas.exceptions.ObjectNotFoundException;
import br.com.macedo.sistemas.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	
	public Produto find(Integer id) {
		
		Optional<Produto> obj = produtoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}

}
