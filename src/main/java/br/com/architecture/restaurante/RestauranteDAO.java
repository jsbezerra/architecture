package br.com.architecture.restaurante;

import javax.inject.Named;

import br.com.architecture.kernel.AbstractDAO;

@Named
public class RestauranteDAO extends AbstractDAO<Restaurante> {

	public RestauranteDAO() {
		super(Restaurante.class);
	}

}
