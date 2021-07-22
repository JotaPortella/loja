package br.com.loja.service;

import java.util.Optional;

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

	public void cadastrarPedido(PedidoDto pedidoDto) {
		Optional<Cliente> clienteOptional = clienteRepository.findById(pedidoDto.getClienteId());

		if (clienteOptional.isPresent()) {
			Pedido pedido = new Pedido();
			pedido.setCliente(clienteOptional.get());

			for (int i = 0; i < pedidoDto.getProdutos().size(); i++) {
				Produto produto = produtoRepository.findById(pedidoDto.getProdutos().get(i)).get();
				pedido.adicionar(new ItemPedido(pedidoDto.getQuantidade().get(i), pedido, produto));
			}
			pedidoRepository.save(pedido);
			enviarPedido(pedido);
		}
	}
	
	public void enviarPedido(Pedido pedido) {
		entregaRepository.save(new Entrega(pedido.getCliente(), pedido));			
	}
}