package br.com.macedo.sistemas.services;

import java.util.Optional;

import br.com.macedo.sistemas.domain.PessoaJuridica;

public interface PjService {
	
	Optional<PessoaJuridica> buscarPorCnpj(String cnpj);
	
	PessoaJuridica persistir(PessoaJuridica pessoaJuridica);

}
