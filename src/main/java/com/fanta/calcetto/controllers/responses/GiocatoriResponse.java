package com.fanta.calcetto.controllers.responses;

import com.fanta.calcetto.entities.Giocatore;

public class GiocatoriResponse {
    private Giocatore giocatore;
    private long ordine;

    public GiocatoriResponse() {
    }

    public Giocatore getGiocatore() {
        return giocatore;
    }

    public void setGiocatore(Giocatore giocatore) {
        this.giocatore = giocatore;
    }

    public long getOrdine() {
        return ordine;
    }

    public void setOrdine(long ordine) {
        this.ordine = ordine;
    }
}
