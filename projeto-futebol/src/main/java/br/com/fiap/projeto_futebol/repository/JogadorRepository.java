package br.com.fiap.projeto_futebol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.projeto_futebol.model.Jogador;

@Repository
public interface JogadorRepository extends JpaRepository<Jogador, Long> {

}
