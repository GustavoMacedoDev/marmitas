package br.com.macedo.sistemas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.macedo.sistemas.domain.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer>{
	
	List<Pagamento> findByMesaId(Integer id);


}
