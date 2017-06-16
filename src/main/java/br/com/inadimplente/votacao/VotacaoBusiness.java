package br.com.inadimplente.votacao;

import br.com.inadimplente.access.Authenticator;
import br.com.inadimplente.restaurante.Restaurante;
import br.com.inadimplente.restaurante.RestauranteDAO;
import br.com.inadimplente.usuario.UsuarioDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.*;

@Named
@ApplicationScoped
public class VotacaoBusiness implements Serializable {

    private static final long serialVersionUID = 1L;

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

    @PostConstruct
    @Transactional
    public void prepararVotacao() {
        /*
        if (!votacaoDAO.hasVotacaoAberta()) {
            Votacao votacao = new Votacao();
            votacao.setData(new Date());
            votacaoDAO.create(votacao);
        }*/
    }

    @Transactional
    public void createNovaVotacao(){
        Votacao votacao = new Votacao();
        votacao.setData(new Date());
        votacao.setAberta(true);
        votacaoDAO.create(votacao);
    }

    @Transactional
    public void closeVotacaoAtual() {
        Votacao votacao = votacaoDAO.findVotacaoAtual();
        votacao.setAberta(false);
        votacao.setVencedor(calcularVencedor(votacao));
        votacaoDAO.update(votacao);
        notificar();
    }

    private void notificar() {
    }

    public Restaurante calcularVencedor(Votacao votacao) {
        List<Voto> votos = votacao.getVotos();
        List<Restaurante> restaurantes = getRestaurantesDisponiveis();
        Map<Restaurante,Integer> contagem = new HashMap<>();
        for (Restaurante r : restaurantes) {
            contagem.put(r, 0);
        }
        for (Voto voto : votos) {
            Restaurante r = voto.getRestaurante();
            contagem.put(r, contagem.get(r) + 1);
        }
        Restaurante vencedor = Collections.max(contagem.entrySet(),
                (r1, r2) -> r1.getValue() - r2.getValue()).getKey();

        return vencedor;
    }

    /*TODO:
        - ocultar botão quando não houver nenhum restaurante
        - não trazer restaurantes já vencedores nessa semana na pesquisa
        - não deixar votar duas vezes*/
    public List<Restaurante> getRestaurantesDisponiveis() {
        return restauranteDAO.listAll();
    }

    public Votacao getVotacaoAtual() {
        return votacaoDAO.findVotacaoAtual();
    }

    public boolean canVote() {
        return getVotacaoAtual().getAberta() && restauranteDAO.count() > 0;
    }

    public Restaurante getRestauranteVencedor() {
        return getVotacaoAtual().getVencedor();
    }
}
