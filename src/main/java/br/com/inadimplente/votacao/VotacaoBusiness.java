package br.com.inadimplente.votacao;

import br.com.inadimplente.access.Authenticator;
import br.com.inadimplente.restaurante.Restaurante;
import br.com.inadimplente.restaurante.RestauranteDAO;
import br.com.inadimplente.usuario.UsuarioDAO;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
public class VotacaoBusiness implements Serializable {

    private static final long serialVersionUID = 1L;

    private Restaurante restaurante;

    @Inject
    private Authenticator authenticator;

    @Inject
    private VotacaoDAO votacaoDAO;

    @Inject
    private RestauranteDAO restauranteDAO;

    @Inject
    private UsuarioDAO usuarioDAO;

    @Inject
    private VotoDAO votoDAO;

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    @PostConstruct
    @Transactional
    public void prepararVotacao() {
        if (!votacaoDAO.hasVotacaoAberta()) {
            Votacao votacao = new Votacao();
            votacao.setData(new Date());
            votacaoDAO.create(votacao);
        }
    }

    @Transactional
    public void createNovaVotacao(){
        Votacao votacao = new Votacao();

    }

    /*TODO:
        - ocultar botão quando não houver nenhum restaurante
        - não trazer restaurantes já vencedores nessa semana na pesquisa
        - não deixar votar duas vezes*/
    public List<Restaurante> restaurantesDisponiveis() {
        return restauranteDAO.listAll();
    }

    public void votar() {
        Voto voto = new Voto();
        voto.setRestaurante(getRestaurante());
        voto.setUsuario(authenticator.getUsuarioLogado());
        voto.setVotacao(votacaoDAO.findVotacaoAtual());
        votoDAO.create(voto);
    }

    public boolean canVote() {
        return restauranteDAO.count() > 0;
    }
}
