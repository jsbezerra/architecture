package br.com.inadimplente.cadastro;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.inadimplente.kernel.AbstractView;

@Named
@ViewScoped
public class CadastroView extends AbstractView<Cadastro> {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CadastroDAO cadastroDAO;

	public CadastroView(Class<Cadastro> entityClass) {
		super(entityClass);
	}

	@SuppressWarnings("unchecked")
	protected CadastroDAO getDao() {
		return cadastroDAO;
	}

}
