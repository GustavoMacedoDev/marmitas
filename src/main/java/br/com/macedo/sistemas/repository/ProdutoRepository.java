package br.com.macedo.sistemas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.macedo.sistemas.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

}
