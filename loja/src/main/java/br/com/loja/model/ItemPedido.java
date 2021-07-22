package br.com.loja.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ItemPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private BigDecimal valor = BigDecimal.ZERO;
	private Integer quantidade;
	@ManyToOne
	private Pedido pedido;
	@ManyToOne
	private Produto produto;

	public ItemPedido() {

	}

	public ItemPedido(Integer quantidade, Pedido pedido, Produto produto) {
		this.valor = produto.getValor();
		this.quantidade = quantidade;
		this.pedido = pedido;
		this.produto = produto;
	}

	public Long getId() {
		return id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setValor(BigDecimal preco) {
		this.valor = preco;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public BigDecimal getValorTotal() {
		return valor.multiply(new BigDecimal(quantidade.intValue()));
	}

}
