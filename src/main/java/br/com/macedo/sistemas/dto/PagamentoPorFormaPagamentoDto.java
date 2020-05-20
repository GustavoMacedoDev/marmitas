package br.com.macedo.sistemas.dto;

import java.io.Serializable;

import br.com.macedo.sistemas.domain.FormaPagamento;

public class PagamentoPorFormaPagamentoDto implements Serializable{

	private static final long serialVersionUID = 1L;

	private double valorTotal;
	private FormaPagamento formaPagamento;
	
	public PagamentoPorFormaPagamentoDto() {
		// TODO Auto-generated constructor stub
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	
}
