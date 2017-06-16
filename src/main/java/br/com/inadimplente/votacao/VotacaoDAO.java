package br.com.inadimplente.votacao;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.inadimplente.kernel.AbstractDAO;
import br.com.inadimplente.restaurante.Restaurante;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named
public class VotacaoDAO extends AbstractDAO<Votacao> {

	public VotacaoDAO() {
		super(Votacao.class);
	}

	public boolean hasVotacaoAberta() {
		Boolean value = getEntityManager()
                .createNamedQuery("votacaoAtual",Votacao.class)
				.getResultList().size() > 0;
		return value;
	}

	public Votacao findVotacaoAtual(){
        List<Votacao> votacao = getEntityManager()
               .createNamedQuery("votacaoAtual", Votacao.class)
               .getResultList();
        if (votacao.size() > 0) {
			return votacao.get(0);
		} else {
        	return null;
		}
    }



    public List<Restaurante> findNUltimosVencedores(Integer numero) {
		List<Vencedor> result = getEntityManager()
                .createQuery("select new br.com.inadimplente.votacao.Vencedor ((v.vencedor), v.data) from Votacao v where " +
                        "v.vencedor is not null order by (v.data) desc")
                .setMaxResults(numero).getResultList();
		List<Restaurante> restaurantes = new ArrayList<>();
		for (Vencedor b : result) {
            restaurantes.add(b.getRestaurante());
        }
		return restaurantes;
	}

	public class  Bean {
	    public Restaurante restaurante;
	    public Date data;

    }


}
