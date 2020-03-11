package br.com.macedo.sistemas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.macedo.sistemas.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
