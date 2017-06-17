package br.com.architecture.restaurante;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.architecture.kernel.AbstractView;

@Named
public class RestauranteView extends AbstractView<Restaurante> {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private RestauranteDAO restauranteDAO;

	public RestauranteView() {
		super(Restaurante.class);
	}

	@SuppressWarnings("unchecked")
	protected RestauranteDAO getDao() {
		return restauranteDAO;
	}

}
