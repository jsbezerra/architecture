package br.com.architecture.votacao;

import br.com.architecture.kernel.AbstractDAO;
import br.com.architecture.usuario.Usuario;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class VotoDAO extends AbstractDAO<Voto> {

    public VotoDAO(){
        super(Voto.class);
    }

    public Voto findByUsusarioAndVotacao(Usuario usuario, Votacao votacao) {
        List<Voto> votos = getEntityManager()
                .createNamedQuery("votoAtualDoUsuario", Voto.class)
                .setParameter("usuario", usuario)
                .setParameter("votacao", votacao)
                .getResultList();
        if (votos.size() > 0) {
            return votos.get(0);
        } else {
            return null;
        }
    }
}
