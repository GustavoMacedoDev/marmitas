package br.com.macedo.sistemas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.macedo.sistemas.domain.Categoria;
import br.com.macedo.sistemas.dto.CategoriaDTO;
import br.com.macedo.sistemas.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return categoriaRepository.save(obj);
	}
	
	public Categoria fromDTO(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(), objDto.getNome());
	}

	public List<Categoria> findAll() {
		
		return this.categoriaRepository.findAll();
	}
	
	public Categoria find(int id) {
		
		
		Categoria categoria = categoriaRepository.findById(id);
		
		return categoria;
	}
}
