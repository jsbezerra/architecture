package br.com.inadimplente.webservice;

import javax.inject.Inject;

import br.com.inadimplente.kernel.AbstractView;

public class WebServiceView extends AbstractView<WebService> {

	private static final long serialVersionUID = 1L;

	@Inject
	private WebServiceDAO webServiceDAO;
	
	public WebServiceView() {
		super(WebService.class);
	}

	@SuppressWarnings("unchecked")
	protected WebServiceDAO getDao() {
		return webServiceDAO;
	}

}
