package br.com.macedo.sistemas.controller;

import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.macedo.sistemas.domain.ItemPedido;
import br.com.macedo.sistemas.domain.OpcaoAtendimento;
import br.com.macedo.sistemas.domain.Pedido;
import br.com.macedo.sistemas.dto.ListaPedidoEntregaDto;
import br.com.macedo.sistemas.dto.PedidoListaDto;
import br.com.macedo.sistemas.dto.PedidoMesaDto;
import br.com.macedo.sistemas.dto.PedidoNewDto;
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
	
	@RequestMapping(value = "/pedido/{id}", method = RequestMethod.GET)
	public @ResponseBody ListaPedidoEntregaDto findById(@PathVariable Integer id) {
		
		ListaPedidoEntregaDto listaPedido = this.pedidoService.findById(id);
		
		return listaPedido;
		
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
			pedidoListaDto.setObservacao(pedidos.get(0).getObservacao());
			return pedidoListaDto;
		}
		
		
	}
	
	@RequestMapping(value = "/pedidoEntrega/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Pedido> findByIdPedido(@PathVariable Integer id) {
		
		Optional<Pedido> pedido = this.pedidoService.findByPedidoId(id);
		
		return pedido;
		
		
	}
	
	
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Validated @RequestBody PedidoNewDto pedidonewDto) {
				
		Pedido pedido = pedidoService.insertEntrega(pedidonewDto);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(pedido.getIdPedido()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	@RequestMapping(value = "/orderMesa", method = RequestMethod.POST)
	public ResponseEntity<Void> salvaPedidoMesa(@Validated @RequestBody PedidoMesaDto pedidoMesaDto) {
		
		System.out.println(pedidoMesaDto.getMesa());
		OpcaoAtendimento op = new OpcaoAtendimento();
		op.setId(3);
		Pedido pedido = new Pedido();
		pedido.setCliente(null);
		pedido.setFormaPagamento(null);
		pedido.setItens(pedidoMesaDto.getItens());
		pedido.setMesa(pedidoMesaDto.getMesa());
		pedido.setOpAtendimento(op);
		pedido.setObservacao(pedidoMesaDto.getObservacao());
		
		pedido = pedidoService.insertMesa(pedido);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(pedido.getIdPedido()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	
	
	
}
