package br.com.macedo.sistemas.domain;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "item_pedido")
public class ItemPedido implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@EmbeddedId
	private ItemPedidoPk id = new ItemPedidoPk();
	
	
	@Column
	private Double desconto;
	@Column
	private Integer quantidade;
	@Column
	private Double preco;

	public ItemPedido() {
		// TODO Auto-generated constructor stub
	}
	
	public ItemPedido(Pedido pedido, Produto produto, Integer quantidade) {
		super();
		id.setPedido(pedido);
		id.setProduto(produto);
		this.quantidade = quantidade;
	}

	@JsonIgnore
	public Pedido getPedido() {
		return id.getPedido();
	}
	public void setPedido(Pedido pedido) {
		id.setPedido(pedido);
	}
	
	public Produto getProduto() {
		return id.getProduto();
	}
	
	public void setProduto(Produto produto) {
		id.setProduto(produto);
	}
	
	public ItemPedidoPk getId() {
		return id;
	}

	public void setId(ItemPedidoPk id) {
		this.id = id;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	
}
