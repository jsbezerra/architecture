package br.com.architecture.votacao;

import br.com.architecture.access.Authenticator;
import br.com.architecture.mail.MailBean;
import br.com.architecture.mail.MailSender;
import br.com.architecture.restaurante.Restaurante;
import br.com.architecture.restaurante.RestauranteDAO;
import br.com.architecture.usuario.Usuario;
import br.com.architecture.usuario.UsuarioDAO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Stateless
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

    @Inject
    private MailSender mailSender;

    @Transactional
    public void createNovaVotacao(){
        if (votacaoDAO.findVotacaoAtual() != null) {
            return;
        }
        Votacao votacao = new Votacao();
        votacao.setData(new Date());
        votacao.setAberta(true);
        votacaoDAO.create(votacao);
    }

    public String bullshit() {
        return "bullshit";
    }

    @Transactional
    public void closeVotacaoAtual() {
        Votacao votacao = votacaoDAO.findVotacaoAtual();
        if (votacao != null) {
            votacao.setAberta(false);
            Restaurante vencedor = calcularVencedor(votacao);
            votacao.setVencedor(vencedor);
            votacaoDAO.update(votacao);
            notificar(vencedor);
        }
    }

    private void notificar(Restaurante vencedor) {
        List<Usuario> usuarios = usuarioDAO.listAll();
        List<String> emails = usuarios.stream().map(u -> u.getEmail()).collect(Collectors.toList());
        String allMails = String.join(", ", emails);
        MailBean mail = new MailBean();
        mail.setTo(allMails);
        mail.setSubject("Resultado da Votação");
        mail.setContent("Olá,\n O Restaurante escolhido na votação de hoje foi o " + vencedor.getNome());
        mailSender.send(mail);
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
        Votacao votacao = votacaoDAO.findVotacaoAtual();
        if (votacao == null) {
            createNovaVotacao();
            votacao = votacaoDAO.findVotacaoAtual();
        }
        return votacao;
    }

    public boolean canVote() {
        return getVotacaoAtual().getAberta() && getRestaurantesDisponiveis().size() > 0;
    }

    public Restaurante getRestauranteVencedor() {
        return getVotacaoAtual().getVencedor();
    }
}
