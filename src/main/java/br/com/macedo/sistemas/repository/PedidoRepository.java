package br.com.macedo.sistemas.repository;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.macedo.sistemas.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

	@Query(value = "SELECT * FROM pedido where op_atendimento_id = ? and status = 0", nativeQuery = true)
	List<Pedido> findByOpAtendimentoId(Integer id);
	
	@Query(value = "SELECT * FROM pedido where mesa_id = ? and status = 0", nativeQuery = true)
	List<Pedido> findByMesaId(Integer id);

	@Transactional
	@Modifying
	@Query(value = "update pedido set status = 1 where mesa_id = ?", nativeQuery = true)
	void fechaPedidos(Integer id);
	
	
}
