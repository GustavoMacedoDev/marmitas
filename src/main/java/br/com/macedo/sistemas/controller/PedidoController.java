package br.com.macedo.sistemas.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.macedo.sistemas.domain.Cliente;
import br.com.macedo.sistemas.domain.FormaPagamento;
import br.com.macedo.sistemas.domain.Pedido;
import br.com.macedo.sistemas.dto.PedidoNewDto;
import br.com.macedo.sistemas.services.ClienteService;
import br.com.macedo.sistemas.services.FormaPagamentoService;
import br.com.macedo.sistemas.services.PedidoService;

@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private FormaPagamentoService formaPagamentoService;
	
	@RequestMapping(value = "/pedido", method = RequestMethod.GET)
	public @ResponseBody List<Pedido> getPedidos() {
		
		return this.pedidoService.findAll();
		
	}
	
	@RequestMapping(value = "/pedido/{id}", method = RequestMethod.GET)
	public @ResponseBody Pedido findById(@PathVariable Integer id) {
		
		Pedido pedido = this.pedidoService.findById(id);
		
		return pedido;
		
	}
	
	
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Valid @RequestBody PedidoNewDto pedidonewDto) {
		String cliente = pedidonewDto.getCliente();
		String formaPagamento = pedidonewDto.getFormaPagamento();
		
		Cliente clienteBuscado = new Cliente();
		clienteBuscado = clienteService.find(Integer.parseInt(cliente));
		
		FormaPagamento formaPagamentoBusca = new FormaPagamento();
		formaPagamentoBusca = formaPagamentoService.find(Integer.parseInt(formaPagamento));
		System.out.println(clienteBuscado.getNome());
		System.out.println(formaPagamentoBusca.getFormaPagamento());
		
		Pedido pedido = new Pedido();
		pedido.setCliente(clienteBuscado);
		pedido.setFormaPagamento(formaPagamentoBusca);
		pedido.setItens(pedidonewDto.getItens());
		
		pedido = pedidoService.insert(pedido);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(pedido.getIdPedido()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	
	
	
}
