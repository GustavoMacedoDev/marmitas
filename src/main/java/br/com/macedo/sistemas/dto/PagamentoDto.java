package br.com.macedo.sistemas.dto;

import java.io.Serializable;

import br.com.macedo.sistemas.domain.Mesa;

public class PagamentoDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String fPagamento;
	private Double valorPago;
	private Mesa mesa;
	private String cliente;
	
	public PagamentoDto() {
		// TODO Auto-generated constructor stub
	}

	public String getfPagamento() {
		return fPagamento;
	}

	public void setfPagamento(String fPagamento) {
		this.fPagamento = fPagamento;
	}

	public Double getValorPago() {
		return valorPago;
	}

	public void setValorPago(Double valorPago) {
		this.valorPago = valorPago;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	
}