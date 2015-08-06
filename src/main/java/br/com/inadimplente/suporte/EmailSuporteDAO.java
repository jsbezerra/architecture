package br.com.inadimplente.suporte;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import br.com.inadimplente.kernel.AbstractDAO;

@Named
public class EmailSuporteDAO extends AbstractDAO<EmailSuporte> {

	@Inject
	private Logger logger;
	
	public EmailSuporteDAO() {
		super(EmailSuporte.class);
	}
	
	@Override
	public void create(EmailSuporte entity) {
		if (count() == 0) {
			super.create(entity);
		} else {
			logger.error("Já existe um e-mail de suporte cadastrado");
			throw new IllegalStateException("Já existe um e-mail de suporte cadastrado");
		}
	}

}
