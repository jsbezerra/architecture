package br.com.inadimplente.access;

import br.com.inadimplente.usuario.TipoUsuario;
import br.com.inadimplente.usuario.Usuario;
import br.com.inadimplente.usuario.UsuarioDAO;
import org.apache.shiro.crypto.hash.Sha256Hash;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
public class Initializer {

    @Inject
    private UsuarioDAO usuarioDAO;

    @PostConstruct
    public void init() {
        if (usuarioDAO.count() == 0) {
            Usuario usuario = new Usuario();
            usuario.setEmail("admin@admin.com");
            usuario.setLogin("admin");
            usuario.setPassword(new Sha256Hash("admin").toHex());
            usuario.setNome("Admin");
            usuario.setTipo(TipoUsuario.ADMIN);
            usuarioDAO.create(usuario);
        }
    }
}
