package br.com.macedo.sistemas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.macedo.sistemas.domain.FormaPagamento;
import br.com.macedo.sistemas.services.FormaPagamentoService;

@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class FormaPagamentoController {
	
	@Autowired
	private FormaPagamentoService formaPagamentoService;
	
	
	@RequestMapping(value = "/formapagamentos", method = RequestMethod.GET)
	public List<FormaPagamento> findAll() {
		return formaPagamentoService.findAll();
	}
	

}
