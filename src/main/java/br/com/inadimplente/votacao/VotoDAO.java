package br.com.inadimplente.votacao;

import br.com.inadimplente.kernel.AbstractDAO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class VotoDAO extends AbstractDAO<Voto> {

    public VotoDAO(){
        super(Voto.class);
    }
}
