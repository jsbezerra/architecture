package br.com.inadimplente.suporte;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.inadimplente.validation.annotation.Email;

@Entity
@Table(name="email_suporte")
public class EmailSuporte {

	@Id
	@SequenceGenerator(name = "EmailSuporteGenerator", sequenceName = "sq_email_suporte", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "EmailSuporteGenerator", strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Short id;
	
	// TODO realizar verificação do formato do e-mail
	@Column(name = "email", nullable = false, unique = true)
	@NotNull
	@Email
	private String email;

	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
