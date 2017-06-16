package br.com.inadimplente.votacao;

import br.com.inadimplente.access.Authenticator;
import br.com.inadimplente.kernel.AbstractView;
import br.com.inadimplente.restaurante.Restaurante;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class VotoView extends AbstractView <Voto> {

    public VotoView() {
        super(Voto.class);
    }

    @Inject
    private VotoDAO votoDAO;

    @Inject
    private VotacaoBusiness votacaoBusiness;

    @Inject
    private Authenticator authenticator;

    private Restaurante restaurante;

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    @Override
    protected VotoDAO getDao() {
        return votoDAO;
    }

    public List<Restaurante> getRestaurantesDisponiveis() {
        return votacaoBusiness.getRestaurantesDisponiveis();
    }

    public Boolean isVotacaoAberta() {
        return votacaoBusiness.canVote();
    }

    @Override
    @PostConstruct
    public void newInstance() {
        Voto voto = votoDAO
                .findByUsusarioAndVotacao(authenticator.getUsuarioLogado(), votacaoBusiness.getVotacaoAtual());
        if (voto == null) {
            super.newInstance();
            getEntity().setVotacao(votacaoBusiness.getVotacaoAtual());
            getEntity().setUsuario(authenticator.getUsuarioLogado());
        } else {
            setEntity(voto);
        }
    }

    @Override
    public void create() {
        getEntity().setRestaurante(getRestaurante());
        if (votacaoBusiness.canVote()) {
            super.create();
        } else {
            getMessagesHandler().error("#{messages['votacao.encerrada']}");
        }
    }

    public void update() {
        getEntity().setRestaurante(getRestaurante());
        if (votacaoBusiness.canVote()) {
            super.update();
        } else {
            getMessagesHandler().error("#{messages['votacao.encerrada']}");
        }
    }

    public Restaurante getVencedor() {
        return votacaoBusiness.getRestauranteVencedor();
    }
}
