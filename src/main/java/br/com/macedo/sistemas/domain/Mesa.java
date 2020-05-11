package br.com.macedo.sistemas.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "mesa")
public class Mesa implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "numero_mesa", unique = true)
	private String numeroMesa;
	@Column(name = "codigo_mesa")
	private int codigoMesa;
	
	@OneToMany(mappedBy = "mesa")
	@JsonIgnore
	private List<Pedido> pedidos = new ArrayList<>();
	
	@OneToMany(mappedBy = "mesa")
	@JsonIgnore
	private List<Pagamento> pagamentos = new ArrayList<>();
	
	@Column(name = "valor_pago_parcial")
	private double valorPagoParcial = 0;
	
	@Column(name = "total_mesa")
	private double totalMesa = 0;
	
	public Mesa() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumeroMesa() {
		return numeroMesa;
	}

	public void setNumeroMesa(String numeroMesa) {
		this.numeroMesa = numeroMesa;
	}

	public int getCodigoMesa() {
		return codigoMesa;
	}

	public void setCodigoMesa(int codigoMesa) {
		this.codigoMesa = codigoMesa;
	}

	
	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}

	public void setPagamentos(List<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}

	public double getValorPagoParcial() {
		return valorPagoParcial;
	}

	public void setValorPagoParcial(double valorPagoParcial) {
		this.valorPagoParcial = valorPagoParcial;
	}

	public double getTotalMesa() {
		return totalMesa;
	}

	public void setTotalMesa(double totalMesa) {
		this.totalMesa = totalMesa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mesa other = (Mesa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
