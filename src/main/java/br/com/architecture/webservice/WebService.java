package br.com.architecture.webservice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "web_service")
public class WebService {

	@Id
	@SequenceGenerator(name = "WebServiceGenerator", sequenceName = "sq_web_service", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "WebServiceGenerator", strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Short id;

	@Column(name = "url", nullable = false, unique = true)
	@NotNull
	private String url;

	@Column(name = "login", nullable = false, unique = true)
	@NotNull
	private String login;

	@Column(name = "senha", nullable = false, unique = true)
	@NotNull
	private String senha;

	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
