package br.com.macedo.sistemas.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import br.com.macedo.sistemas.domain.Cliente;
import br.com.macedo.sistemas.domain.FormaPagamento;
import br.com.macedo.sistemas.domain.ItemPedido;
import br.com.macedo.sistemas.domain.Produto;

public class PedidoNewDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Cliente cliente;
	private FormaPagamento formaPagamento;
	private Integer quantidade;
	private Date instante;
	private Produto produto;
	private Set<ItemPedido> itens;
	private Double totalPedido;
	private Double valorPago;
	
	
	public PedidoNewDto() {
		// TODO Auto-generated constructor stub
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Date getInstante() {
		return instante;
	}
	public void setInstante(Date instante) {
		this.instante = instante;
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
	public Double getTotalPedido() {
		return totalPedido;
	}
	public void setTotalPedido(Double totalPedido) {
		this.totalPedido = totalPedido;
	}
	public Double getValorPago() {
		return valorPago;
	}
	public void setValorPago(Double valorPago) {
		this.valorPago = valorPago;
	}
	
}
