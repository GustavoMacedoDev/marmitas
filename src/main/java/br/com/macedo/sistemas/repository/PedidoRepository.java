package br.com.macedo.sistemas.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.macedo.sistemas.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

	List<Pedido> findByOpAtendimentoId(Integer id);
	
	
	
}
