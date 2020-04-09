package br.com.macedo.sistemas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.macedo.sistemas.domain.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

}
