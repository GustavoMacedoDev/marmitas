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

import br.com.macedo.sistemas.domain.Pedido;
import br.com.macedo.sistemas.services.PedidoService;

@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;
	
	@RequestMapping(value = "/pedido", method = RequestMethod.GET)
	public @ResponseBody List<Pedido> getPedidos() {
		
		return this.pedidoService.findAll();
		
	}
	
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Valid @RequestBody Pedido pedido) {
		
		pedido = pedidoService.insert(pedido);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(pedido.getIdPedido()).toUri();
		return ResponseEntity.created(uri).build();
	}

}
