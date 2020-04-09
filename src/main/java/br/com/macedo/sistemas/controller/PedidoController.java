package br.com.macedo.sistemas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.macedo.sistemas.domain.Pedido;
import br.com.macedo.sistemas.model.ResponseModel;
import br.com.macedo.sistemas.repository.PedidoRepository;

@CrossOrigin(origins = "http://10.0.0.41:4200/", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class PedidoController {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	
	@RequestMapping(value = "/pedido", method = RequestMethod.GET)
	public @ResponseBody List<Pedido> getPedidos() {
		
		return this.pedidoRepository.findAll();
		
	}
	
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public @ResponseBody ResponseModel salvar(@RequestBody Pedido pedido){
		
		try {
			
			this.pedidoRepository.save(pedido);
			
			return new ResponseModel(1, "Sucesso ao salvar ordem");
			
		}catch(Exception e) {
			
			return new ResponseModel(0, "Erro ao salvar");			
		}
	}
	

}
