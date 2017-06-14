package br.com.inadimplente.votacao;

import javax.inject.Named;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;

import br.com.inadimplente.kernel.AbstractDAO;

@Named
public class VotacaoDAO extends AbstractDAO<Votacao> {

	public VotacaoDAO() {
		super(Votacao.class);
	}

	public boolean hasVotacaoAberta() {
		return getEntityManager().createNamedQuery("votacaoAtual",Votacao.class)
				.getResultList().size() > 0;
	}

	public Votacao findVotacaoAtual(){
        return getEntityManager()
               .createNamedQuery("votacaoAtual", Votacao.class)
               .getSingleResult();
    }


}
