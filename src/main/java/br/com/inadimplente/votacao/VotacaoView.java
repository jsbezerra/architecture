package br.com.inadimplente.votacao;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.inadimplente.kernel.AbstractView;

import java.util.Date;

@Named
public class VotacaoView extends AbstractView<Votacao> {

	private static final long serialVersionUID = 1L;

	private Date date = new Date();

	@Inject
	private VotacaoDAO votacaoDAO;

	public VotacaoView() {
		super(Votacao.class);
	}

	@SuppressWarnings("unchecked")
	protected VotacaoDAO getDao() {
		return votacaoDAO;
	}

	private void novaVotacao() {
	    Votacao votacao = new Votacao();
	    votacao.setData(new Date());
	    getDao().create(votacao);
    }
}
