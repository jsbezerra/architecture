package br.com.inadimplente.usuario;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.inadimplente.kernel.AbstractDAO;

@Named
@RequestScoped
public class UsuarioDAO extends AbstractDAO<Usuario> {

	public UsuarioDAO() {
		super(Usuario.class);
	}

}
