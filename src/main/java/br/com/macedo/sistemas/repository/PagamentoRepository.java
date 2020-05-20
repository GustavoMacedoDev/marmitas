package br.com.macedo.sistemas.repository;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.macedo.sistemas.domain.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer>{
	
	@Query(value = "SELECT * FROM pagamento where mesa_id = ? and status = 0", nativeQuery = true)
	List<Pagamento> findByMesaId(Integer id);
	
	@Query(value = "select * from sum_valor_pago_by_forma_pagamento", nativeQuery = true) 
	List<Pagamento> buscaPagamentos();

	List<Pagamento> findByPedidoIdPedido(@Valid Integer id);


}
