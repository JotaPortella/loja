package br.com.loja.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.loja.dto.PedidoDto;
import br.com.loja.model.Cliente;
import br.com.loja.model.Entrega;
import br.com.loja.model.ItemPedido;
import br.com.loja.model.Pedido;
import br.com.loja.model.Produto;
import br.com.loja.repository.ClienteRepository;
import br.com.loja.repository.EntregaRepository;
import br.com.loja.repository.PedidoRepository;
import br.com.loja.repository.ProdutoRepository;

@Service
public class PedidoService {
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EntregaRepository entregaRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private RabbitMQSender rabbitMQSender;

	public void enviarPedido(Pedido pedido) {
		entregaRepository.save(new Entrega(pedido.getCliente(), pedido));
	}

	public void cadastrarPedido(PedidoDto pedidoDto) throws EntityNotFoundException {
		Cliente cliente = clienteRepository.findById(pedidoDto.getClienteId())
				.orElseThrow(EntityNotFoundException::new);

		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);

		pedidoDto.getItens().stream()
				.forEach(item -> adicionarItemPedido(pedido, item.getProdutoId(), item.getQuantidade()));

		pedidoRepository.save(pedido);
		rabbitMQSender.send(pedido);
	}

	public void adicionarItemPedido(Pedido pedido, Long produtoId, Integer quantidade) throws EntityNotFoundException {
		Produto produto = produtoRepository.findById(produtoId).orElseThrow(EntityNotFoundException::new);
		pedido.adicionar(new ItemPedido(pedido, produto, quantidade));
	}

}