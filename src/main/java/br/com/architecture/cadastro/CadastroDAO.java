package br.com.architecture.cadastro;

import javax.inject.Named;

import br.com.architecture.kernel.AbstractDAO;

@Named
public class CadastroDAO extends AbstractDAO<Cadastro> {

	public CadastroDAO() {
		super(Cadastro.class);
	}

}
