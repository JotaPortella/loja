package br.com.loja.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Entrega {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Cliente cliente;
	@OneToOne
	private Pedido pedido;

	public Entrega() {

	}

	public Entrega(Cliente cliente, Pedido pedido) {
		this.cliente = cliente;
		this.pedido = pedido;
	}

	public Long getId() {
		return id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Pedido getPedid() {
		return pedido;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setPedid(Pedido pedid) {
		this.pedido = pedid;
	}

}
