package br.com.inadimplente.access;

import br.com.inadimplente.restaurante.Restaurante;
import br.com.inadimplente.restaurante.RestauranteDAO;
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

    @Inject
    private RestauranteDAO restauranteDAO;

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
        if (restauranteDAO.count() == 0) {
            Restaurante r = new Restaurante();
            Restaurante r2 = new Restaurante();
            r.setNome("Borsatto");
            r.setSituacao(true);
            r2.setNome("Le beef");
            r2.setSituacao(true);
            restauranteDAO.create(r);
            restauranteDAO.create(r2);
        }
    }
}
