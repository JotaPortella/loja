package br.com.loja.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.loja.model.Pedido;

public interface PedidoRepository extends CrudRepository<Pedido, Long> {

}
