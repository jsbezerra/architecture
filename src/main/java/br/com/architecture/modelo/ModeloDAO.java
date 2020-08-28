package br.com.architecture.modelo;

import javax.inject.Named;

import br.com.architecture.kernel.AbstractDAO;

@Named
public class ModeloDAO extends AbstractDAO<Modelo> {

	public ModeloDAO() {
		super(Modelo.class);
	}

}
