package br.com.architecture.usuario;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.shiro.crypto.hash.Sha256Hash;

import br.com.architecture.kernel.AbstractView;
import br.com.architecture.mail.MailBean;
import br.com.architecture.mail.MailSender;

@Named
@ViewScoped
public class UsuarioView extends AbstractView<Usuario> {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioDAO usuarioDAO;
	@Inject
	private MailSender mailSender;

	public UsuarioView() {
		super(Usuario.class);
	}
	
	@SuppressWarnings("unchecked")
	protected UsuarioDAO getDao() {
		return usuarioDAO;
	}
	
	public TipoUsuario[] getTiposDeUsuarios() {
		return TipoUsuario.values();
	}
	
	@Override
	public void create() {
		getEntity().setPassword(new Sha256Hash("secret").toHex());
		super.create();
		MailBean mail = new MailBean();
		mail.setTo(getEntity().getEmail());
		mail.setSubject("Senha");
		mail.setContent("Sua nova senha é 'secret'.");
		mailSender.send(mail);
	}

}
