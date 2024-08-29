package br.com.fiap.projeto_futebol.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "times")
public class Time {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "Não é possível cadastrar um time sem nome")
	@Size(min = 3, max = 255, message = "Não é possível cadastrar um time com menos de 3 letras")
	private String nome;
	@Enumerated(EnumType.STRING)
	private ListaUF uf;
	@NotEmpty(message = "Não é possível cadastrar um hino sem valor")
	private String hino;
	@NotEmpty(message = "Não é possível cadastrar um brasão sem valor")
	private String brasao;

	public Time() {

	}

	public Time(Long id, String nome, ListaUF uf, String hino, String brasao) {
		super();
		this.id = id;
		this.nome = nome;
		this.uf = uf;
		this.hino = hino;
		this.brasao = brasao;
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

	public ListaUF getUf() {
		return uf;
	}

	public void setUf(ListaUF uf) {
		this.uf = uf;
	}

	public String getHino() {
		return hino;
	}

	public void setHino(String hino) {
		this.hino = hino;
	}

	public String getBrasao() {
		return brasao;
	}

	public void setBrasao(String brasao) {
		this.brasao = brasao;
	}

}