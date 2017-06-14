package br.com.inadimplente.restaurante;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "restaurantes")
public class Restaurante {

	@Id
	@SequenceGenerator(name = "RestaurantesGenerator", sequenceName = "sq_restaurantes", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "RestaurantesGenerator", strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Long id;

	@Column(name = "nome", nullable = false)
	@NotNull
	private String nome;

	@Column(name = "situacao", nullable = false)
	@NotNull
	private Boolean situacao;

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

	public Boolean getSituacao() {
		return situacao;
	}

	public void setSituacao(Boolean situacao) {
		this.situacao = situacao;
	}
	
	@Override
	public String toString() {
		return getNome().toString();
	}

}
