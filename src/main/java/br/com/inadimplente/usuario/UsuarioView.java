package br.com.inadimplente.usuario;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.inadimplente.kernel.AbstractView;

@Named
@ViewScoped
public class UsuarioView extends AbstractView<Usuario> {

	private static final long serialVersionUID = 1L;

	public UsuarioView() {
		super(Usuario.class);
	}

	@Inject
	private UsuarioDAO usuarioDAO;
	
	@SuppressWarnings("unchecked")
	protected UsuarioDAO getDao() {
		return usuarioDAO;
	}
	
	public TipoUsuario[] getTiposDeUsuarios() {
		return TipoUsuario.values();
	}

}
