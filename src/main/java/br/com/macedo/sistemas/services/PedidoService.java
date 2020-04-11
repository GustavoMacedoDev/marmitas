package br.com.macedo.sistemas.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.macedo.sistemas.domain.Cliente;
import br.com.macedo.sistemas.domain.Endereco;
import br.com.macedo.sistemas.domain.ItemPedido;
import br.com.macedo.sistemas.domain.Pedido;
import br.com.macedo.sistemas.dto.ClienteNewDto;
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
	private ClienteService clienteService;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private FormaPagamentoService formaPagamentoService;
	
	public List<Pedido> findAll() {
		return this.pedidoRepository.findAll();
	}
	
	public Pedido insert(Pedido obj) {
		
		System.out.println(obj.getCliente());
		
		obj.setIdPedido(null);
		obj.setInstante(new Date());
		obj.setFormaPagamento(formaPagamentoService.find(obj.getFormaPagamento().getId()));
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		obj = pedidoRepository.save(obj);
		
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.find(ip.getProduto().getIdProduto()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		return obj;
	}

	
}
