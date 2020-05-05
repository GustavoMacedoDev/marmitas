package br.com.macedo.sistemas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.macedo.sistemas.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{
	
	@Query(value = "select * from produto p where p.categoria_id = ?1", nativeQuery = true)
	List<Produto> findByCategoriaId(Integer id);
	
	List<Produto> findAllByStatus(int status);

	@Query(value = "update produto set status = 1 where id_produto = ?1", nativeQuery = true)
	Produto inativaProduto(Integer id);

	@Query(value = "select * from produto p where p.categoria_id = 1 OR p.categoria_id = 6", nativeQuery = true)
	List<Produto> findAllQuiosque();

	@Query(value = "select * from produto p where p.categoria_id = 1 OR p.categoria_id = 4 OR p.categoria_id = 5", nativeQuery = true)
	List<Produto> findAllEntrega();

}
