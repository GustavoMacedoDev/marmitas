package br.com.macedo.sistemas.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.macedo.sistemas.domain.PessoaJuridica;
import br.com.macedo.sistemas.repository.PjRepository;
import br.com.macedo.sistemas.services.PjService;

@Service
public class PjServiceImpl implements PjService{

	private static final Logger log = LoggerFactory.getLogger(PjServiceImpl.class);

	@Autowired
	private  PjRepository pjRepository;

	@Override
	public Optional<PessoaJuridica> buscarPorCnpj(String cnpj) {
		log.info("Buscando uma empresa para o CNPJ {}", cnpj);
		return Optional.ofNullable(pjRepository.findByCnpj(cnpj));
	}

	@Override
	public PessoaJuridica persistir(PessoaJuridica pessoaJuridica) {
		log.info("Persistindo empresa: {}", pessoaJuridica);
		return this.pjRepository.save(pessoaJuridica);
	}

}
