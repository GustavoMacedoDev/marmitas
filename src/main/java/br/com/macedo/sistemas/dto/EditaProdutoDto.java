package br.com.macedo.sistemas.dto;

import java.io.Serializable;

public class EditaProdutoDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String nome;
	private Double preco;
	
	public EditaProdutoDto() {
		// TODO Auto-generated constructor stub
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
}
