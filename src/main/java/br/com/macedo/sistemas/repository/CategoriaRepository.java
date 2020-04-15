package br.com.macedo.sistemas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.macedo.sistemas.domain.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

	@Transactional(readOnly = true)
	Categoria findById(int id);
	
}
