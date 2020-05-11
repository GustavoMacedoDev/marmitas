package br.com.macedo.sistemas.controller;

import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
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
import br.com.macedo.sistemas.domain.ItemPedido;
import br.com.macedo.sistemas.domain.OpcaoAtendimento;
import br.com.macedo.sistemas.domain.Pedido;
import br.com.macedo.sistemas.dto.PedidoListaDto;
import br.com.macedo.sistemas.dto.PedidoMesaDto;
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
	
	@RequestMapping(value = "/pedidoOpcao/{id}", method = RequestMethod.GET)
	public @ResponseBody List<Pedido> findByOpAtendimento(@PathVariable Integer id) {
		
		List<Pedido> pedidos = this.pedidoService.findByOpAtendimentoId(id);
		
		return pedidos;
		
	}
	
	@RequestMapping(value = "/pedidosMesa/{id}", method = RequestMethod.GET)
	public @ResponseBody PedidoListaDto findByIdMesa(@PathVariable Integer id) {
		
		List<Pedido> pedidos = this.pedidoService.findByMesaId(id);
		
		if(pedidos.isEmpty()) {
			return null;
		} else {
			PedidoListaDto pedidoListaDto = new PedidoListaDto();
			
			Set<ItemPedido> itens = new HashSet<>();
			for(Pedido peds: pedidos) {
				
				itens.addAll(peds.getItens());
				
			}
			
			pedidoListaDto.setItens(itens);
			pedidoListaDto.setInstante(pedidos.get(0).getInstante());
			pedidoListaDto.setMesa(pedidos.get(0).getMesa());
			
			double totalMesa = pedidos.get(0).getMesa().getTotalMesa();
			double valorPagoParcial = pedidos.get(0).getMesa().getValorPagoParcial();
			
			double valorEmAberto = totalMesa - valorPagoParcial;
			
			pedidoListaDto.setValorEmAberto(valorEmAberto);
			pedidoListaDto.setValorTotalPedido(pedidos.get(0).getTotalPedido());		
			return pedidoListaDto;
		}
		
		
	}
	
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Valid @RequestBody PedidoNewDto pedidonewDto) {
		String cliente = pedidonewDto.getCliente();
		String formaPagamento = pedidonewDto.getFormaPagamento();
		
		Cliente clienteBuscado = new Cliente();
		clienteBuscado = clienteService.find(Integer.parseInt(cliente));
		
		FormaPagamento formaPagamentoBusca = new FormaPagamento();
		formaPagamentoBusca = formaPagamentoService.find(Integer.parseInt(formaPagamento));
		
		OpcaoAtendimento op = new OpcaoAtendimento();
		op.setId(2);
		Pedido pedido = new Pedido();
		pedido.setCliente(clienteBuscado);
		pedido.setFormaPagamento(formaPagamentoBusca);
		pedido.setItens(pedidonewDto.getItens());
		pedido.setOpAtendimento(op);
		pedido = pedidoService.insertEntrega(pedido);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(pedido.getIdPedido()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	@RequestMapping(value = "/orderMesa", method = RequestMethod.POST)
	public ResponseEntity<Void> salvaPedidoMesa(@Valid @RequestBody PedidoMesaDto pedidoMesaDto) {
		
		System.out.println(pedidoMesaDto.getMesa());
		OpcaoAtendimento op = new OpcaoAtendimento();
		op.setId(3);
		Pedido pedido = new Pedido();
		pedido.setCliente(null);
		pedido.setFormaPagamento(null);
		pedido.setItens(pedidoMesaDto.getItens());
		pedido.setMesa(pedidoMesaDto.getMesa());
		pedido.setOpAtendimento(op);
		
		pedido = pedidoService.insertMesa(pedido);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(pedido.getIdPedido()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	
	
	
}
