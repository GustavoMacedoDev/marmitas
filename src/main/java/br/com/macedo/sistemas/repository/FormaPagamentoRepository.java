package br.com.macedo.sistemas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.macedo.sistemas.domain.FormaPagamento;

public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Integer>{

}
