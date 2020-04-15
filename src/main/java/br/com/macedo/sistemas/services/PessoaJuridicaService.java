package br.com.macedo.sistemas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.macedo.sistemas.domain.PessoaJuridica;
import br.com.macedo.sistemas.repository.PjRepository;

@Service
public class PessoaJuridicaService {
	
	@Autowired
	private PjRepository pjRepository;
	
	public List<PessoaJuridica> findAll() {
		return this.pjRepository.findAll();
	}
	
	public Optional<PessoaJuridica> find(Integer id) {
		
		Optional<PessoaJuridica> pessoaJuridica = pjRepository.findById(id);
		return pessoaJuridica;
	}

}
