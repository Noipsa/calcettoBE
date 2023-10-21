package com.fanta.calcetto.controllers.requests;

import com.fanta.calcetto.entities.Giocatore;
import com.fanta.calcetto.entities.Utente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class AcquistoGiocatoriRequest {
    private Giocatore giocatore;
    private Utente utente;

    public AcquistoGiocatoriRequest() {
    }

    public Giocatore getGiocatore() {
        return giocatore;
    }

    public void setGiocatore(Giocatore giocatore) {
        this.giocatore = giocatore;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }
}
