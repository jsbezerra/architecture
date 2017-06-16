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
import java.util.stream.Collectors;

@Named
@ApplicationScoped
public class VotacaoBusiness implements Serializable {

    private static final long serialVersionUID = 1L;

    private static Integer PRAZO = 7;

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
        Restaurante vencedor = calcularVencedor(votacao);
        votacao.setVencedor(vencedor);
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

    public List<Restaurante> getRestaurantesDisponiveis() {
        List<Restaurante> ultimos = votacaoDAO.findNUltimosVencedores(PRAZO);
        List<Restaurante> todos = restauranteDAO.listAll();
        return todos.stream()
                .filter(r -> !ultimos.contains(r))
                .collect(Collectors.toList());
    }

    public Votacao getVotacaoAtual() {
        return votacaoDAO.findVotacaoAtual();
    }

    public boolean canVote() {
        return getVotacaoAtual().getAberta() && getRestaurantesDisponiveis().size() > 0;
    }

    public Restaurante getRestauranteVencedor() {
        return getVotacaoAtual().getVencedor();
    }
}
