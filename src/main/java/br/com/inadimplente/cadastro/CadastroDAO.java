package br.com.inadimplente.cadastro;

import javax.inject.Named;

import br.com.inadimplente.kernel.AbstractDAO;

@Named
public class CadastroDAO extends AbstractDAO<Cadastro> {

	public CadastroDAO() {
		super(Cadastro.class);
	}

}
