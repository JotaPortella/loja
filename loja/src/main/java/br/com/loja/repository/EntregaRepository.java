package br.com.loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.loja.model.Entrega;

public interface EntregaRepository extends JpaRepository<Entrega, Long> {

}
