package br.com.inadimplente.votacao;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.inadimplente.restaurante.Restaurante;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "votacoes")
@NamedQuery(name = "votacaoAtual", query = "select v from Votacao v where v.data = CURRENT_DATE")
public class Votacao {

	public Votacao() {
		this.data = new Date();
	}

	@Id
	@SequenceGenerator(name = "VotacoesGenerator", sequenceName = "sq_votacoes", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator="VotacoesGenerator", strategy=GenerationType.SEQUENCE)
	@Column(name="id")
	private Long id;

	@Temporal(TemporalType.DATE)
	@NotNull
	@Column(name = "data", unique = true, nullable = false)
	private Date data;

    @OneToMany(mappedBy = "votacao")
	private List<Voto> votos;

    @ManyToOne
    @JoinColumn(name = "restaurante_vencedor", nullable = true)
    private Restaurante vencedor;

    @Column(name = "aberta", nullable = false)
    @NotNull
    private Boolean aberta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public List<Voto> getVotos() {
        return votos;
    }

    public void setVotos(List<Voto> votos) {
        this.votos = votos;
    }

    public Restaurante getVencedor() {
        return vencedor;
    }

    public void setVencedor(Restaurante vencedor) {
        this.vencedor = vencedor;
    }

    public Boolean getAberta() {
        return aberta;
    }

    public void setAberta(Boolean aberta) {
        this.aberta = aberta;
    }

}
