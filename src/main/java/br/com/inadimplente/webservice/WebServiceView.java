package br.com.inadimplente.webservice;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.inadimplente.kernel.AbstractView;

@Named
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
	
	@Override
	@PostConstruct
	public void newInstance() {
		super.newInstance();
		List<WebService> lista = getDao().listAll();
		if (!lista.isEmpty()) {
			setEntity(lista.get(0));
		}
	}

}
