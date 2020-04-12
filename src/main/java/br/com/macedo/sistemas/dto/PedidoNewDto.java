package br.com.macedo.sistemas.dto;

import java.io.Serializable;
import java.util.Set;

import br.com.macedo.sistemas.domain.ItemPedido;
import br.com.macedo.sistemas.domain.Produto;

public class PedidoNewDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String cliente;
	private String formaPagamento;
	private Integer quantidade;
	private Produto produto;
	private Set<ItemPedido> itens;
	
	
	public PedidoNewDto() {
		// TODO Auto-generated constructor stub
	}
	
	
	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Set<ItemPedido> getItens() {
		return itens;
	}
	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}
	
}
