package br.com.loja.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.loja.dto.ItemProdutoDto;
import br.com.loja.dto.PedidoDto;
import br.com.loja.model.Cliente;
import br.com.loja.model.ItemPedido;
import br.com.loja.model.Pedido;
import br.com.loja.model.Produto;
import br.com.loja.repository.ClienteRepository;
import br.com.loja.repository.EntregaRepository;
import br.com.loja.repository.PedidoRepository;
import br.com.loja.repository.ProdutoRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class PedidoServiceTest {
	private static final long id = 1;

	@InjectMocks
	private PedidoService service;
	@Mock
	private ClienteRepository clienteRepository;
	@Mock
	private EntregaRepository entregaRepository;
	@Mock
	private PedidoRepository pedidoRepository;
	@Mock
	private ProdutoRepository produtoRepository;
	@Mock
	private RabbitMQSender rabbitMQSender;

	@Test
	public void cadastrarPedido_deveSalvarPedidoNoBanco() {
		when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(umCliente()));
		when(produtoRepository.findById(anyLong())).thenReturn(Optional.of(umProduto()));

		service.cadastrarPedido(umPedidoDto());
		verify(pedidoRepository, times(1)).save(any());
	}

	@Test
	public void cadastrarPedido_deveEnviarUmaMenssagemNaFila() {
		when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(umCliente()));
		when(produtoRepository.findById(anyLong())).thenReturn(Optional.of(umProduto()));

		service.cadastrarPedido(umPedidoDto());
		verify(rabbitMQSender, times(1)).send(any());

	}

	@Test
	public void cadastrarPedido_deveLancarEntityNotFoundException_quandoClienteInvalido() {
		try {
			service.cadastrarPedido(new PedidoDto());
		} catch (EntityNotFoundException e) {

		}

	}

	@Test
	public void enviarPedido_deveSalvarEntregaNoBanco() {
		service.enviarPedido(umPedido());
		verify(entregaRepository, times(1)).save(any());
	}

	@Test
	public void adicionarItemPedido_deveSalvarItemPedidoNoPedido() {
		when(produtoRepository.findById(anyLong())).thenReturn(Optional.of(umProduto()));

		Pedido pedido = umPedido();

		service.adicionarItemPedido(pedido, id, 1);

		assertEquals(pedido.getItens().get(0).getId(), umItemPedido().getId());
		assertEquals(pedido.getItens().get(0).getValor(), umItemPedido().getValor());
		assertEquals(pedido.getItens().get(0).getPedido(), umItemPedido().getPedido());
		assertEquals(pedido.getItens().get(0).getProduto().getId(), umItemPedido().getProduto().getId());
		assertEquals(pedido.getItens().get(0).getQuantidade(), umItemPedido().getQuantidade());

	}

	private static Pedido umPedido() {
		Pedido pedido = new Pedido();
		pedido.setId(id);
		pedido.setCliente(umCliente());
		pedido.adicionar(umItemPedido());
		return pedido;
	}

	private static ItemPedido umItemPedido() {
		ItemPedido item = new ItemPedido();
		item.setId(id);
		item.setProduto(umProduto());
		item.setValor(umProduto().getValor());
		item.setQuantidade(1);
		return item;
	}

	private static Produto umProduto() {
		Produto produto = new Produto();
		produto.setId(id);
		produto.setNome("produto");
		produto.setValor(new BigDecimal(999));
		return produto;
	}

	private static Cliente umCliente() {
		Cliente cliente = new Cliente();
		cliente.setId(id);
		cliente.setNome("cliente");
		cliente.setEndereco("endereço, 999");
		cliente.setBairro("bairro");
		cliente.setCep("99999999");
		cliente.setCidade("cidade");
		cliente.setEstado("estado");
		return cliente;
	}

	private static PedidoDto umPedidoDto() {
		PedidoDto pedidoDto = new PedidoDto();
		pedidoDto.setClienteId(id);
		pedidoDto.setItens(umaListaItemProdutoDto());
		return pedidoDto;
	}

	private static List<ItemProdutoDto> umaListaItemProdutoDto() {
		ItemProdutoDto item = new ItemProdutoDto();
		item.setProdutoId(id);
		item.setQuantidade(1);
		List<ItemProdutoDto> lista = new ArrayList<ItemProdutoDto>();
		lista.add(item);
		return lista;
	}

}
