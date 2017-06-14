package br.com.inadimplente.restaurante;

import javax.inject.Named;

import br.com.inadimplente.kernel.AbstractDAO;

import java.util.List;

@Named
public class RestauranteDAO extends AbstractDAO<Restaurante> {

	public RestauranteDAO() {
		super(Restaurante.class);
	}

}
