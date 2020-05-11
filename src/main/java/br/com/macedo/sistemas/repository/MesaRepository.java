package br.com.macedo.sistemas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.macedo.sistemas.domain.Mesa;

public interface MesaRepository extends JpaRepository<Mesa, Integer>{

	List<Mesa> findAllByOrderByIdAsc();
	
	
}
