package br.com.inadimplente.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.inadimplente.restaurante.Restaurante;

@Entity
@Table(name = "modelos")
public class Modelo {

	@Id
	@SequenceGenerator(name = "ModelosGenerator", sequenceName = "sq_modelos", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator="ModelosGenerator", strategy=GenerationType.SEQUENCE)
	@Column(name="id")
	private Long id;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="restaurante", unique=true, nullable=false)
	@NotNull
	private Restaurante restaurante;
	
	@Column(name="mensagem", nullable=false, unique=false, length=160)
	@NotNull
	@Size(min=1, max=160)
	private String mensagem;
	
	@Column(name="prazo", nullable=false, unique=false)
	@NotNull
	private Integer prazo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Integer getPrazo() {
		return prazo;
	}

	public void setPrazo(Integer prazo) {
		this.prazo = prazo;
	}
	
	

}
