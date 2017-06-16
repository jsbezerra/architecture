package br.com.inadimplente.kernel.starter;

import br.com.inadimplente.restaurante.Restaurante;
import br.com.inadimplente.restaurante.RestauranteDAO;
import br.com.inadimplente.usuario.TipoUsuario;
import br.com.inadimplente.usuario.Usuario;
import br.com.inadimplente.usuario.UsuarioDAO;
import br.com.inadimplente.votacao.Votacao;
import br.com.inadimplente.votacao.VotacaoDAO;
import org.apache.shiro.crypto.hash.Sha256Hash;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Singleton
@Startup
public class Initializer {

    @Inject
    private UsuarioDAO usuarioDAO;

    @Inject
    private RestauranteDAO restauranteDAO;

    @Inject
    private VotacaoDAO votacaoDAO;

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

            usuario = new Usuario();
            usuario.setEmail("jonas@jonas.com");
            usuario.setLogin("jonas");
            usuario.setPassword(new Sha256Hash("jonas").toHex());
            usuario.setNome("Jonas");
            usuario.setTipo(TipoUsuario.NORMAL);
            usuarioDAO.create(usuario);

            usuario = new Usuario();
            usuario.setEmail("gabriel@gabriel.com");
            usuario.setLogin("gabriel");
            usuario.setPassword(new Sha256Hash("gabriel").toHex());
            usuario.setNome("gabriel");
            usuario.setTipo(TipoUsuario.NORMAL);
            usuarioDAO.create(usuario);

            usuario = new Usuario();
            usuario.setEmail("wendell@wendell.com");
            usuario.setLogin("wendell");
            usuario.setPassword(new Sha256Hash("wendell").toHex());
            usuario.setNome("wendell");
            usuario.setTipo(TipoUsuario.NORMAL);
            usuarioDAO.create(usuario);
        }
        if (restauranteDAO.count() == 0) {
            Restaurante r = new Restaurante();
            r.setNome("Borsatto");
            r.setSituacao(true);
            restauranteDAO.create(r);

            r = new Restaurante();
            r.setNome("Le beef");
            r.setSituacao(true);
            restauranteDAO.create(r);

            r = new Restaurante();
            r.setNome("Sal & Brasa");
            r.setSituacao(true);
            restauranteDAO.create(r);

            r = new Restaurante();
            r.setNome("Panorama");
            r.setSituacao(true);
            restauranteDAO.create(r);

            Votacao v = new Votacao();
            v.setAberta(false);
            v.setVencedor(r);
            Calendar calendar = new GregorianCalendar(2017,6,15);
            v.setData(calendar.getTime());
            votacaoDAO.create(v);
        }
    }
}
