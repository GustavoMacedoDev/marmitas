package br.com.macedo.sistemas.dto;

public class ProdutoNewDto {

	private String nome;
	private Double preco;
	private int categoria;
	private int restaurante;
	
	public ProdutoNewDto() {
		// TODO Auto-generated constructor stub
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

	public int getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(int restaurante) {
		this.restaurante = restaurante;
	}


}
