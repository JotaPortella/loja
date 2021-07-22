package br.com.loja.dto;

import java.util.List;

public class PedidoDto {

	private Long clienteId;
	private List<Long> produtos;
	private List<Integer> quantidade;

	public Long getClienteId() {
		return clienteId;
	}

	public List<Long> getProdutos() {
		return produtos;
	}

	public List<Integer> getQuantidade() {
		return quantidade;
	}

}
