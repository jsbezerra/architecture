package br.com.architecture.modelo;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.architecture.kernel.AbstractView;

@Named
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
