package br.com.fiap.projeto_futebol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.projeto_futebol.model.Time;

@Repository
public interface TimeRepository extends JpaRepository<Time, Long>{

}
