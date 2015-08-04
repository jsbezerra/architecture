package br.com.inadimplente.security;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.credential.Password;
import org.picketlink.idm.model.basic.User;

import br.com.inadimplente.usuario.Usuario;

@Named
@RequestScoped
public class UserRegistrator {

	@Inject
	private IdentityManager identityManager;
	
	@Transactional
	public void register(Usuario usuario) {
		User user = new User(usuario.getLogin());
		user.setFirstName(usuario.getNome());
		this.identityManager.add(user);
		Password password = new Password(usuario.getLogin());
		this.identityManager.updateCredential(user, password);
	}
	
}
