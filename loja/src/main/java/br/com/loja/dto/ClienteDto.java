package br.com.loja.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.loja.model.Cliente;

public class ClienteDto {

	private Long id;
	private String nome;
	private String cpf;
	private String endereco;
	private String bairro;
	private String cep;
	private String cidade;
	private String estado;

	public ClienteDto(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.cpf = cliente.getCpf();
		this.endereco = cliente.getEndereco();
		this.bairro = cliente.getBairro();
		this.cep = cliente.getCep();
		this.cidade = cliente.getCidade();
		this.estado = cliente.getEstado();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public String getCep() {
		return cep;
	}

	public String getCidade() {
		return cidade;
	}

	public String getEstado() {
		return estado;
	}

	public static List<ClienteDto> of(List<Cliente> cliente) {
		return cliente.stream().map(ClienteDto::new).collect(Collectors.toList());
	}

}
