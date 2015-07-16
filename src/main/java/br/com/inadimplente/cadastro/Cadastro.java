package br.com.inadimplente.cadastro;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="cadastros")
public class Cadastro {

	@Id
	@SequenceGenerator(name="CadastrosGenerator", sequenceName="sq_cadastros", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="CadastrosGenerator", strategy=GenerationType.SEQUENCE)
	@Column(name="codigo")
	private Integer codigo;

	@Column(name="nome", nullable=false)
	@NotNull
	private String nome;
	
	@Column(name="situacao", nullable=false)
	@NotNull
	private Boolean situacao;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
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

}
