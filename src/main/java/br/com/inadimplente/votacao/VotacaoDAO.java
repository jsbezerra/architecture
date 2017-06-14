package br.com.inadimplente.votacao;

import javax.inject.Named;

import br.com.inadimplente.kernel.AbstractDAO;

@Named
public class VotacaoDAO extends AbstractDAO<Votacao> {

	public VotacaoDAO() {
		super(Votacao.class);
	}

	public Votacao findVotacaoAtual(){
        return getEntityManager()
               .createQuery("select v from Votacao v where v.data = CURRENT_DATE", Votacao.class)
               .getSingleResult();
    }


}
