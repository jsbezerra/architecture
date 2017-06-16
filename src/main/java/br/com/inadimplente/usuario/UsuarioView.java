package br.com.inadimplente.usuario;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.shiro.crypto.hash.Sha256Hash;

import br.com.inadimplente.kernel.AbstractView;
import br.com.inadimplente.mail.MailBean;
import br.com.inadimplente.mail.MailSender;

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
