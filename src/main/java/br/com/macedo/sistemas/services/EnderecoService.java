package br.com.macedo.sistemas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.macedo.sistemas.domain.Endereco;
import br.com.macedo.sistemas.repository.EnderecoRepository;

@Service
public class EnderecoService {
	
	@Autowired
	private EnderecoRepository endRepository;
	
	public Endereco insert(Endereco endereco) {
	
		
		this.endRepository.save(endereco);
		
		return endereco;
	}

}
