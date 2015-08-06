package br.com.inadimplente.webservice;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import br.com.inadimplente.kernel.AbstractDAO;

@Named
public class WebServiceDAO extends AbstractDAO<WebService> {
	
	@Inject
	private Logger logger;

	public WebServiceDAO() {
		super(WebService.class);
	}

	@Override
	public void create(WebService entity) {
		if (count() == 0) {
			super.create(entity);
		} else {
			logger.error("Já existe um webservice cadastrado");
			throw new IllegalStateException("Já existe um webservice cadastrado");
		}
	}

}
