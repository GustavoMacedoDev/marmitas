package br.com.macedo.sistemas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.macedo.sistemas.domain.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer>{
	
	@Query(value = "SELECT * FROM pagamento where mesa_id = ? and status = 0", nativeQuery = true)
	List<Pagamento> findByMesaId(Integer id);
	
	


}
