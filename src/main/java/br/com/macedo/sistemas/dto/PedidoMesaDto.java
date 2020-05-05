package br.com.macedo.sistemas.dto;

import java.io.Serializable;
import java.util.Set;

import br.com.macedo.sistemas.domain.ItemPedido;
import br.com.macedo.sistemas.domain.Mesa;

public class PedidoMesaDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Mesa mesa;
	private Set<ItemPedido> itens;
	
	public PedidoMesaDto() {
		// TODO Auto-generated constructor stub
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}
	
	

}
