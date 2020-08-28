package br.com.architecture.cadastro;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.architecture.kernel.AbstractView;

@Named
public class CadastroView extends AbstractView<Cadastro> {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CadastroDAO cadastroDAO;

	public CadastroView() {
		super(Cadastro.class);
	}

	@SuppressWarnings("unchecked")
	protected CadastroDAO getDao() {
		return cadastroDAO;
	}

}
