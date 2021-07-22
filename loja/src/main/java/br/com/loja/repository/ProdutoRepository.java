package br.com.loja.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.loja.model.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, Long> {

}
