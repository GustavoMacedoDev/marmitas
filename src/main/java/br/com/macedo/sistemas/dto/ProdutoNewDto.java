package br.com.macedo.sistemas.dto;

import br.com.macedo.sistemas.domain.Categoria;
import br.com.macedo.sistemas.domain.OpcaoAtendimento;
import br.com.macedo.sistemas.domain.PessoaJuridica;

public class ProdutoNewDto {

	private String nome;
	private Double preco;
	private Categoria categoria;
	private PessoaJuridica restaurante;
	private OpcaoAtendimento opcao;
	
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

	
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public PessoaJuridica getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(PessoaJuridica restaurante) {
		this.restaurante = restaurante;
	}

	public OpcaoAtendimento getOpcao() {
		return opcao;
	}

	public void setOpcao(OpcaoAtendimento opcao) {
		this.opcao = opcao;
	}

}
