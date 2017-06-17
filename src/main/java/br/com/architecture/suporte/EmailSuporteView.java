package br.com.architecture.suporte;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.architecture.kernel.AbstractView;

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
	
	@Override
	@PostConstruct
	public void newInstance() {
		super.newInstance();
		List<EmailSuporte> lista = getDao().listAll();
		if (!lista.isEmpty()) {
			setEntity(lista.get(0));
		}
	}

}
