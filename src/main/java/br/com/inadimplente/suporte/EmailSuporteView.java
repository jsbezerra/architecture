package br.com.inadimplente.suporte;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.inadimplente.kernel.AbstractView;

@Named
public class EmailSuporteView extends AbstractView<EmailSuporte> {

	private static final long serialVersionUID = 1L;

	@Inject
	private EmailSuporteDAO emailSuporteDAO;

	public EmailSuporteView() {
		super(EmailSuporte.class);
	}

	@SuppressWarnings("unchecked")
	protected EmailSuporteDAO getDao() {
		return emailSuporteDAO;
	}

}
