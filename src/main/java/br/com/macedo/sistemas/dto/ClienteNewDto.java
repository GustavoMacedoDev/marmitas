package br.com.macedo.sistemas.dto;

import java.io.Serializable;

public class ClienteNewDto implements Serializable{

	private static final long serialVersionUID = 1L;

	private String nome;
	
	private String logradouro;

	private String numero;

	private String complemento;

	private String bairro;
	
	private Long telefone;

	public ClienteNewDto() {
		// TODO Auto-generated constructor stub
	}
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Long getTelefone() {
		return telefone;
	}

	public void setTelefone(Long telefone) {
		this.telefone = telefone;
	}

	
}
