package br.com.macedo.sistemas.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.macedo.sistemas.domain.ItemPedido;
import br.com.macedo.sistemas.domain.Pedido;
import br.com.macedo.sistemas.repository.ItemPedidoRepository;
import br.com.macedo.sistemas.repository.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private FormaPagamentoService formaPagamentoService;
	
	public List<Pedido> findAll() {
		return this.pedidoRepository.findAll();
	}
	
	public Pedido findById(Integer id) {
		
		Optional<Pedido> pedido = this.pedidoRepository.findById(id);

		Pedido ped = new Pedido();
		ped.setCliente(pedido.get().getCliente());
		ped.setFormaPagamento(pedido.get().getFormaPagamento());
		ped.setInstante(pedido.get().getInstante());
		
		double soma = 0;
		
		for(ItemPedido it: pedido.get().getItens()) {
			double preco = it.getPreco();
			int quantidade = it.getQuantidade();
			double total = preco * quantidade;
			soma += total;
			
		}
		ped.setIdPedido(pedido.get().getIdPedido());
		ped.setTotalPedido(soma);
		ped.setItens(pedido.get().getItens());
		
		return ped;		
	}
	
	
	public Pedido insert(Pedido obj) {
		
		obj.setIdPedido(null);
		obj.setInstante(new Date());
		obj.setFormaPagamento(formaPagamentoService.find(obj.getFormaPagamento().getId()));
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		obj = pedidoRepository.save(obj);
		
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			System.out.println(ip.getProduto().getId());
			ip.setProduto(produtoService.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		return obj;
	}

	
}
