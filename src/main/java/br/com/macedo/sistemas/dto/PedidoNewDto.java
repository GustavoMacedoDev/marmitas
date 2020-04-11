package br.com.macedo.sistemas.dto;

import java.text.Normalizer.Form;

import br.com.macedo.sistemas.domain.Cliente;
import br.com.macedo.sistemas.domain.FormaPagamento;
import br.com.macedo.sistemas.domain.Produto;

public class PedidoNewDto {
	
	private Cliente cliente;
	private FormaPagamento formaPagamento;
	private Integer quantidade;
	private Produto produto;
	
	
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
	
}
