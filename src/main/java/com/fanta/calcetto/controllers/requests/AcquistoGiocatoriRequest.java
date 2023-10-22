package com.fanta.calcetto.controllers.requests;

import com.fanta.calcetto.entities.Giocatore;
import com.fanta.calcetto.entities.Utente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class AcquistoGiocatoriRequest {
    private Giocatore giocatore;
    private long id_utente;

    public AcquistoGiocatoriRequest() {
    }

    public Giocatore getGiocatore() {
        return giocatore;
    }

    public void setGiocatore(Giocatore giocatore) {
        this.giocatore = giocatore;
    }

    public long getId_utente() {
        return id_utente;
    }

    public void setId_utente(long utente) {
        this.id_utente = utente;
    }
}
