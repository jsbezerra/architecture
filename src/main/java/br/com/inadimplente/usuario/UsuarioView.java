package br.com.inadimplente.usuario;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.inadimplente.kernel.AbstractView;
import br.com.inadimplente.security.UserRegistrator;

@Named
@ViewScoped
public class UsuarioView extends AbstractView<Usuario> {
	
	@Inject
	private UserRegistrator userRegistrator;

	public UsuarioView() {
		super(Usuario.class);
	}

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioDAO usuarioDAO;
	
	@SuppressWarnings("unchecked")
	protected UsuarioDAO getDao() {
		return usuarioDAO;
	}
	
	public TipoUsuario[] getTiposDeUsuarios() {
		return TipoUsuario.values();
	}
	
	@Override
	public void create() {
		super.create();
		userRegistrator.register(getEntity());
	}

}
