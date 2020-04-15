package br.com.macedo.sistemas.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.macedo.sistemas.domain.PessoaJuridica;

public interface PjRepository extends JpaRepository<PessoaJuridica, Integer>{
	
	@Transactional(readOnly = true)
	PessoaJuridica findByCnpj(String cnpj);

}
