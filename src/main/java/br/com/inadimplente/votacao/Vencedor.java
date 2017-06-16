package br.com.inadimplente.votacao;

import br.com.inadimplente.restaurante.Restaurante;

import java.util.Date;

public class Vencedor {
    private Restaurante restaurante;
    private Date data;

    public Vencedor(Restaurante restaurante, Date data) {
        this.restaurante = restaurante;
        this.data = data;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }
}
