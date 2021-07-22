package br.com.loja.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.loja.dto.ClienteDto;
import br.com.loja.model.Cliente;
import br.com.loja.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository repository;

	@GetMapping("/listar")
	public List<ClienteDto> clienteDto() {
		List<Cliente> cliente = repository.findAll();
		return ClienteDto.of(cliente);
	}
}
