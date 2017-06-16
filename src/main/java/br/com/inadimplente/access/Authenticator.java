package br.com.inadimplente.access;

import br.com.inadimplente.usuario.Usuario;
import br.com.inadimplente.usuario.UsuarioDAO;
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

}
