package br.com.macedo.sistemas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.macedo.sistemas.domain.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer>{
	
	@Query(value = "select * from item_pedido i where i.pedido_id = ?1", nativeQuery = true)
	List<ItemPedido> buscaPedido(Integer idPedido);
	

}
