package br.com.macedo.sistemas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.macedo.sistemas.domain.Cliente;
import br.com.macedo.sistemas.domain.FormaPagamento;
import br.com.macedo.sistemas.exceptions.ObjectNotFoundException;
import br.com.macedo.sistemas.repository.FormaPagamentoRepository;

@Service
public class FormaPagamentoService {

	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	
	public List<FormaPagamento> findAll() {
		return formaPagamentoRepository.findAll();
	}
	
	
	public FormaPagamento find(Integer id) {
		Optional<FormaPagamento> obj = formaPagamentoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
}
