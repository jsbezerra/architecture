package br.com.inadimplente.modelo;

import javax.inject.Named;

import br.com.inadimplente.kernel.AbstractDAO;

@Named
public class ModeloDAO extends AbstractDAO<Modelo> {

	public ModeloDAO() {
		super(Modelo.class);
	}

}
