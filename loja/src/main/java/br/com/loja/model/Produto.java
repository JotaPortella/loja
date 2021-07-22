package br.com.loja.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sun.istack.NotNull;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@NotNull
	private BigDecimal valor;

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
