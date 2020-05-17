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
	
	public Optional<Pedido> findById(Integer id) {
		
		Optional<Pedido> pedido = this.pedidoRepository.findById(id);

		/*Pedido ped = new Pedido();
		ped.setCliente(pedido.get().getCliente());
		ped.setFormaPagamento(pedido.get().getFormaPagamento());
		ped.setInstante(pedido.get().getInstante());
		ped.setMesa(pedido.get().getMesa());
		
		ped.setIdPedido(pedido.get().getIdPedido());
		ped.setTotalPedido(pedido.get().getTotalPedido());
		ped.setItens(pedido.get().getItens());
		
		*/
		
		return pedido;		
	}
	
	
	public Pedido insertEntrega(PedidoNewDto obj) {
		
		OpcaoAtendimento op = new OpcaoAtendimento();
		op.setId(2);
		
		Pedido pedido = new Pedido();
		pedido.setInstante(new Date());
		pedido.setCliente(obj.getCliente());
		pedido.setFormaPagamento(obj.getFormaPagamento());
		pedido.setOpAtendimento(op);
		pedido.setTotalPedido(obj.getTotalPedido());
		pedido.setValorPago(obj.getValorPago());

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
