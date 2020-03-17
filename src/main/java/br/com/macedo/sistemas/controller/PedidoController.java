package br.com.macedo.sistemas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.macedo.sistemas.domain.Pedido;
import br.com.macedo.sistemas.repository.PedidoRepository;

@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
@RestController
@RequestMapping("/service")
public class PedidoController {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	
	@RequestMapping(value = "/pedido", method = RequestMethod.GET)
	public @ResponseBody List<Pedido> getPedidos() {
		
		return this.pedidoRepository.findAll();
		
	}
	
	

}
