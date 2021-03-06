package br.com.architecture.restaurante;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "restaurantes")
public class Restaurante implements Serializable {

    public Restaurante() {
        this.situacao = true;
    }

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
		return getNome();
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Restaurante)) return false;

        Restaurante that = (Restaurante) o;

        if (!getId().equals(that.getId())) return false;
        if (!getNome().equals(that.getNome())) return false;
        return getSituacao().equals(that.getSituacao());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getNome().hashCode();
        result = 31 * result + getSituacao().hashCode();
        return result;
    }
}
