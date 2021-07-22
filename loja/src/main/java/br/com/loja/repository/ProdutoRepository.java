package br.com.loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.loja.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
