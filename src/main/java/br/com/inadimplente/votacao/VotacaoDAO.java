package br.com.inadimplente.votacao;

import javax.inject.Named;

import br.com.inadimplente.kernel.AbstractDAO;

@Named
public class VotacaoDAO extends AbstractDAO<Votacao> {

	public VotacaoDAO() {
		super(Votacao.class);
	}

}
