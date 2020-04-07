package br.com.macedo.sistemas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.macedo.sistemas.domain.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer>{

}
