package br.com.fiap.projeto_futebol.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;

@Entity
@Table(name = "jogador")
public class Jogador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@ManyToOne
	@JoinColumn(name = "id_time")
	@Valid
	private Time time;
	private Integer numero;
	
	public Jogador() {

	}

	public Jogador(Long id, String nome, Time time) {
		super();
		this.id = id;
		this.nome = nome;
		this.time = time;
	}

	public Jogador(Long id, String nome, Time time, Integer numero) {
		super();
		this.id = id;
		this.nome = nome;
		this.time = time;
		this.numero = numero;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

}
