package br.com.macedo.sistemas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.macedo.sistemas.domain.OpcaoAtendimento;
import br.com.macedo.sistemas.repository.OpcaoAtendimentoRepository;

@Service
public class OpcaoAtendimentoService {
	
	@Autowired
	private OpcaoAtendimentoRepository opRepository;
	
	public List<OpcaoAtendimento> findAll() {
		return this.opRepository.findAll();
	}

}
