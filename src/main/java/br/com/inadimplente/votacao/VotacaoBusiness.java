package br.com.inadimplente.votacao;

import br.com.inadimplente.restaurante.Restaurante;
import br.com.inadimplente.restaurante.RestauranteDAO;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.List;

@Named
public class VotacaoBusiness {

    private Restaurante restaurante;

    @Inject
    private VotacaoDAO votacaoDAO;

    @Inject
    private RestauranteDAO restauranteDAO;

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public VotacaoDAO getVotacaoDAO() {
        return votacaoDAO;
    }

    public void setVotacaoDAO(VotacaoDAO votacaoDAO) {
        this.votacaoDAO = votacaoDAO;
    }

    public RestauranteDAO getRestauranteDAO() {
        return restauranteDAO;
    }

    public void setRestauranteDAO(RestauranteDAO restauranteDAO) {
        this.restauranteDAO = restauranteDAO;
    }

    @Transactional
    public void createNovaVotacao(){
        Votacao votacao = new Votacao();

    }

    public List<Restaurante> restaurantesDisponiveis() {
        return restauranteDAO.listAll();
    }
}
