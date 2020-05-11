package br.com.macedo.sistemas.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import br.com.macedo.sistemas.domain.ItemPedido;
import br.com.macedo.sistemas.domain.Mesa;

public class PedidoListaDto {
	
	private Mesa mesa;
	private Date instante;
	private Set<ItemPedido> itens = new HashSet<>();
	private double valorEmAberto;
	private double valorTotalPedido;
	
	
	public PedidoListaDto() {
	}
	
	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	public Date getInstante() {
		return instante;
	}

	public void setInstante(Date instante) {
		this.instante = instante;
	}

	public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}

	public double getValorEmAberto() {
		return valorEmAberto;
	}

	public void setValorEmAberto(double valorEmAberto) {
		this.valorEmAberto = valorEmAberto;
	}

	public double getValorTotalPedido() {
		return valorTotalPedido;
	}

	public void setValorTotalPedido(double valorTotalPedido) {
		this.valorTotalPedido = valorTotalPedido;
	}
	
	
	
}
