package br.com.inadimplente.votacao;

import br.com.inadimplente.access.Authenticator;
import br.com.inadimplente.mail.MailBean;
import br.com.inadimplente.mail.MailSender;
import br.com.inadimplente.restaurante.Restaurante;
import br.com.inadimplente.restaurante.RestauranteDAO;
import br.com.inadimplente.usuario.Usuario;
import br.com.inadimplente.usuario.UsuarioDAO;

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
        notificar(vencedor);
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
        return votacaoDAO.findVotacaoAtual();
    }

    public boolean canVote() {
        return getVotacaoAtual().getAberta() && getRestaurantesDisponiveis().size() > 0;
    }

    public Restaurante getRestauranteVencedor() {
        return getVotacaoAtual().getVencedor();
    }
}
