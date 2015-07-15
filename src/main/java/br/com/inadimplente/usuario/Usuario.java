package br.com.inadimplente.usuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="usuarios")
public class Usuario {
	
	@Id
	@SequenceGenerator(name="UsuariosGenerator", sequenceName="sq_usuarios", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="UsuariosGenerator", strategy=GenerationType.SEQUENCE)
	@Column(name="id")
	private Integer id;
	
	@Column(name="nome", nullable=false, unique=false)
	@NotNull
	private String nome;
	
	@Column(name="email", nullable=false, unique=true)
	@NotNull
	private String email;
	
	@Column(name="tipo", nullable=false, unique=false)
	@Enumerated(EnumType.STRING)
	@NotNull
	private TipoUsuario tipo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}

	
	
}