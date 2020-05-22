package br.com.macedo.sistemas.dto;

public class PagamentoEntregaDto {

	private String cliente;
	private String fPagamento;
	private Double valorPago;
	private Integer idPedido;
	
	public PagamentoEntregaDto() {
		// TODO Auto-generated constructor stub
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
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

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}
	
}
