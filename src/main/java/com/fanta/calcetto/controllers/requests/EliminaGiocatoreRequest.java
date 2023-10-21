package com.fanta.calcetto.controllers.requests;

import com.fanta.calcetto.entities.Giocatore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class EliminaGiocatoreRequest {
    private Giocatore giocatore;
    private long id_utente;

    public EliminaGiocatoreRequest() {
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

    public void setId_utente(long id_utente) {
        this.id_utente = id_utente;
    }
}
