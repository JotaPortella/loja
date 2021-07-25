package br.com.loja.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Cliente cliente;
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<ItemPedido> itens = new ArrayList<>();
	private BigDecimal valorTotal = BigDecimal.ZERO;

	public Long getId() {
		return id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public List<ItemPedido> getItens() {
		return itens;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public void adicionar(ItemPedido item) {
		if (item.getQuantidade() < 1) {
			throw new IllegalArgumentException("Quantidade deve ser positiva e diferente de 0 (zero)");
		}
		this.itens.add(item);
		this.valorTotal = this.valorTotal.add(item.getValorTotal());
	}

}
