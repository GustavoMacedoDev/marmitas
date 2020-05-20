package br.com.macedo.sistemas.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.macedo.sistemas.domain.ItemPedido;
import br.com.macedo.sistemas.domain.Mesa;
import br.com.macedo.sistemas.domain.OpcaoAtendimento;
import br.com.macedo.sistemas.domain.Pedido;
import br.com.macedo.sistemas.dto.ListaPedidoEntregaDto;
import br.com.macedo.sistemas.dto.PedidoNewDto;
import br.com.macedo.sistemas.repository.ItemPedidoRepository;
import br.com.macedo.sistemas.repository.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private MesaService mesaService;
	
	public List<Pedido> findAll() {
		return this.pedidoRepository.findAll();
	}
	
	public ListaPedidoEntregaDto findById(Integer id) {
		
		Pedido pedido = this.pedidoRepository.getOne(id);

		ListaPedidoEntregaDto listaPedido = new ListaPedidoEntregaDto();
		listaPedido.setIdPedido(pedido.getIdPedido());
		listaPedido.setInstante(pedido.getInstante());
		listaPedido.setCliente(pedido.getCliente());
		listaPedido.setItens(pedido.getItens());
		listaPedido.setFormaPagamento(pedido.getFormaPagamento());
		listaPedido.setTotalPedido(pedido.getTotalPedido());
		listaPedido.setValorPago(pedido.getValorPago());
		listaPedido.setObservacao(pedido.getObservacao());
		
		return listaPedido;		
	}
	
	
	public Pedido insertEntrega(PedidoNewDto obj) {
		
		OpcaoAtendimento op = new OpcaoAtendimento();
		op.setId(2);
		Pedido pedido = new Pedido();
		
		if(obj.getValorPago() != null)  {
			System.out.println("oi meu chaapa");
			pedido.setValorPago(obj.getValorPago());
		} else {
			pedido.setValorPago(obj.getTotalPedido());
		}
		
		
		pedido.setInstante(new Date());
		pedido.setCliente(obj.getCliente());
		pedido.setFormaPagamento(obj.getFormaPagamento());
		pedido.setOpAtendimento(op);
		pedido.setTotalPedido(obj.getTotalPedido());
				
				
		pedido.setObservacao(obj.getObservacao());

		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(pedido);
			
		}
		
		pedido = pedidoRepository.save(pedido);
		itemPedidoRepository.saveAll(obj.getItens());
		
		return pedido;
	}
	
	public Pedido insertMesa(Pedido obj) {
		
		obj.setIdPedido(null);
		obj.setInstante(new Date());
		obj.setFormaPagamento(null);
		obj.setCliente(null);
		obj.setOpAtendimento(obj.getOpAtendimento());
		
		double soma = 0;
		
		for (ItemPedido ip : obj.getItens()) {
			
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			double preco = ip.getProduto().getPreco();
			int quantidade = ip.getQuantidade();
			double total = preco * quantidade;
			soma += total;
			ip.setPedido(obj);
		}
		
		
		
		
		obj.setTotalPedido(soma);
		obj = pedidoRepository.save(obj);
		itemPedidoRepository.saveAll(obj.getItens());
		
		atualizaMesa(obj);
		return obj;
	}

	private void atualizaMesa(Pedido obj) {
		mesaService.atualizaPagamentosMesa(obj);
	}

	public List<Pedido> findByOpAtendimentoId(Integer id) {
		return this.pedidoRepository.findByOpAtendimentoId(id);
	}

	public List<Pedido> findByMesaId(Integer id) {
		
		return this.pedidoRepository.findByMesaId(id);
	}

	public void fechaPedidos(Mesa mesa) {
		this.pedidoRepository.fechaPedidos(mesa.getId());
	}

	public Optional<Pedido> findByPedidoId(Integer id) {
		return this.pedidoRepository.findById(id);
				
	}
	
}
