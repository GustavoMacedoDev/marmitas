package br.com.macedo.sistemas.repository;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.macedo.sistemas.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

	List<Pedido> findByOpAtendimentoId(Integer id);
	
	List<Pedido> findByMesaId(Integer id);

	@Transactional
	@Modifying
	@Query(value = "update pedido set status = 1 where mesa_id = ?", nativeQuery = true)
	void fechaPedidos(Integer id);
	
	
}
