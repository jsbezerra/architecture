package br.com.inadimplente.webservice;

import javax.inject.Inject;

import br.com.inadimplente.kernel.AbstractView;

public class WebServiceView extends AbstractView<WebService> {

	private static final long serialVersionUID = 1L;

	@Inject
	private WebServiceDAO webServiceDAO;
	
	public WebServiceView(Class<WebService> entityClass) {
		super(entityClass);
	}

	@SuppressWarnings("unchecked")
	protected WebServiceDAO getDao() {
		return webServiceDAO;
	}

}
