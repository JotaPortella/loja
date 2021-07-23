package br.com.loja.dto;

import java.util.List;

public class PedidoDto {

	private Long clienteId;
	private List<ItemProdutoDto> itens;

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public void setItens(List<ItemProdutoDto> itens) {
		this.itens = itens;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public List<ItemProdutoDto> getItens() {
		return itens;
	}

}
