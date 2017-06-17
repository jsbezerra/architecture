package br.com.architecture.access;

import br.com.architecture.usuario.TipoUsuario;
import br.com.architecture.usuario.Usuario;
import br.com.architecture.usuario.UsuarioDAO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class Authenticator {

    @Inject
    private UsuarioDAO usuarioDAO;

    //TODO: redirecionar quando o usuário não houver logado
    public Usuario getUsuarioLogado() {
        Subject currentUser = SecurityUtils.getSubject();
        String login = currentUser.getPrincipal().toString();
        return usuarioDAO.findByLogin(login);
    }

    public Boolean isAdmin() {
        return getUsuarioLogado().getTipo().equals(TipoUsuario.ADMIN);
    }

}
