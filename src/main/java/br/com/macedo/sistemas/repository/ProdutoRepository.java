package br.com.macedo.sistemas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.macedo.sistemas.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{
	
	@Query(value = "select * from produto p where p.categoria_id = ?1", nativeQuery = true)
	List<Produto> findByCategoriaId(Integer id);

}
