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
@Table(name = "usuarios")
public class Usuario {

	@Id
	@SequenceGenerator(name = "UsuariosGenerator", sequenceName = "sq_usuarios", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "UsuariosGenerator", strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Long id;

	@Column(name = "nome", nullable = false, unique = false)
	@NotNull
	private String nome;

	@Column(name = "login", nullable = false, unique = true)
	@NotNull
	private String login;

	// TODO realizar verificação do formato do e-mail
	@Column(name = "email", nullable = false, unique = true)
	@NotNull
	private String email;
	
	//TODO adicionar Salt
	@Column(name = "password", nullable = false, unique=false)
	private String password;

	@Column(name = "tipo", nullable = false, unique = false)
	@Enumerated(EnumType.STRING)
	@NotNull
	private TipoUsuario tipo;

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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}

}
