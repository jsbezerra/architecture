package br.com.inadimplente.votacao;

import br.com.inadimplente.restaurante.Restaurante;
import br.com.inadimplente.restaurante.RestauranteDAO;
import br.com.inadimplente.usuario.UsuarioDAO;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Named
public class VotacaoBusiness {

    private Restaurante restaurante;

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
        - não trazer restaurantes já vencedores nessa semana na pesquisa */
    public List<Restaurante> restaurantesDisponiveis() {
        return restauranteDAO.listAll();
    }

    public void votar() {
        Voto voto = new Voto();
        voto.setRestaurante(getRestaurante());
        voto.setUsuario(usuarioDAO.findByLogin("jonas"));
        voto.setVotacao(votacaoDAO.findVotacaoAtual());
        votoDAO.create(voto);
    }

    public boolean canVote() {
        return restauranteDAO.count() > 0;
    }
}
