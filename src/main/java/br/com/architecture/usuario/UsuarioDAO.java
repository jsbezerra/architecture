package br.com.architecture.usuario;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.TypedQuery;

import br.com.architecture.kernel.AbstractDAO;

@Named
@RequestScoped
public class UsuarioDAO extends AbstractDAO<Usuario> {

	public UsuarioDAO() {
		super(Usuario.class);
	}

	public Usuario findByLogin(String login) {
		TypedQuery<Usuario> query = getEntityManager().createQuery("select u from Usuario u where u.login = :login", Usuario.class);
		query.setParameter("login", login);
		return query.getResultList().get(0);
	}

}
