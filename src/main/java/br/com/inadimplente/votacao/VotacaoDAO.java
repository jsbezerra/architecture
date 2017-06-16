package br.com.inadimplente.votacao;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;

import br.com.inadimplente.kernel.AbstractDAO;

@Named
@ApplicationScoped
public class VotacaoDAO extends AbstractDAO<Votacao> {

	public VotacaoDAO() {
		super(Votacao.class);
	}

	public boolean hasVotacaoAberta() {
		Boolean value = getEntityManager().createNamedQuery("votacaoAtual",Votacao.class)
				.getResultList().size() > 0;
		return value;
	}

	public Votacao findVotacaoAtual(){
        return getEntityManager()
               .createNamedQuery("votacaoAtual", Votacao.class)
               .getSingleResult();
    }


}
