package br.com.loja.dto;

public class ItemProdutoDto {

	private Long produtoId;
	private Integer quantidade;

	public Long getProdutoId() {
		return produtoId;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setProdutoId(Long produtoId) {
		this.produtoId = produtoId;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

}
