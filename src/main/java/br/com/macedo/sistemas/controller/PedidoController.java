package br.com.macedo.sistemas.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.macedo.sistemas.domain.Cliente;
import br.com.macedo.sistemas.domain.Pedido;
import br.com.macedo.sistemas.dto.ClienteNewDto;
import br.com.macedo.sistemas.dto.PedidoNewDto;
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
	
	/*@RequestMapping(value = "/order", method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody PedidoNewDto pedidoNewDto) {
		
		
		
		Cliente obj = clienteService.fromDTO(objDto);
		obj = clienteService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}*/
	

}
