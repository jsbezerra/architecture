package br.com.architecture.usuario;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.architecture.kernel.AbstractDAO;

@Named
@RequestScoped
public class UsuarioDAO extends AbstractDAO<Usuario> {

	public UsuarioDAO() {
		super(Usuario.class);
	}

}
