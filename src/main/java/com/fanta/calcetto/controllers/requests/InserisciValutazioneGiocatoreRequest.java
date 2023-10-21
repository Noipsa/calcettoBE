package com.fanta.calcetto.controllers.requests;

import com.fanta.calcetto.entities.Giocatore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class InserisciValutazioneGiocatoreRequest {
    private Giocatore giocatore;
    private long valutazione;

    public InserisciValutazioneGiocatoreRequest() {
    }

    public Giocatore getGiocatore() {
        return giocatore;
    }

    public void setGiocatore(Giocatore giocatore) {
        this.giocatore = giocatore;
    }

    public long getValutazione() {
        return valutazione;
    }

    public void setValutazione(long valutazione) {
        this.valutazione = valutazione;
    }
}
