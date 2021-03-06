package com.ecomerce.dominio;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.ecomerce.dominio.enums.PagamentoStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Pagamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	private Instant moment;
	private Integer estado;

	@OneToOne
	@MapsId
	@JoinColumn(name = "pedido_id")
	@JsonIgnore
	private Pedido pedido;

	public Pagamento() {

	}

	public Pagamento(Integer id, Instant moment, Pedido pedido, PagamentoStatus estado) {
		super();
		this.id = id;
		this.moment = moment;
		this.pedido = pedido;
		this.estado = estado.getCode();
		//setPagamentoStatus(pagamentoStatus);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	public PagamentoStatus getPagamentoStatus() {
		return PagamentoStatus.toEnum(estado);
	}
	
	public void setEstado(PagamentoStatus estado) {
		if (estado != null) {
			this.estado = estado.getCode();
		}
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
		Pagamento other = (Pagamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}