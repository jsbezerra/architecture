package br.com.inadimplente.modelo;

import javax.inject.Inject;

import br.com.inadimplente.kernel.AbstractView;

public class ModeloView extends AbstractView<Modelo> {

	private static final long serialVersionUID = 1L;

	@Inject
	private ModeloDAO modeloDAO;

	public ModeloView() {
		super(Modelo.class);
	}

	@SuppressWarnings("unchecked")
	protected ModeloDAO getDao() {
		return modeloDAO;
	}

}
