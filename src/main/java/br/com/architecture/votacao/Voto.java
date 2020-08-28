package br.com.architecture.votacao;

import br.com.architecture.restaurante.Restaurante;
import br.com.architecture.usuario.Usuario;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NamedQuery(name="votoAtualDoUsuario", query = "select v from Voto v where v.usuario = :usuario and v.votacao = :votacao")
@Table(name = "votos")
public class Voto {

    @Id
    @SequenceGenerator(name = "VotosGenerator", sequenceName = "sq_votos", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator="VotosGenerator", strategy= GenerationType.SEQUENCE)
    @Column(name="id")
    private Long id;

    @ManyToOne
    @NotNull
    @JoinColumn(name="usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @NotNull
    @JoinColumn(name="restaurante", nullable = false)
    private Restaurante restaurante;

    @ManyToOne
    @NotNull
    @JoinColumn(name="votacao", nullable = false)
    private Votacao votacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public Votacao getVotacao() {
        return votacao;
    }

    public void setVotacao(Votacao votacao) {
        this.votacao = votacao;
    }
}
