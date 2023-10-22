package com.fanta.calcetto.controllers.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class AggiornaTitolariRequest extends AcquistoGiocatoriRequest{
    private long id_utente;

    public AggiornaTitolariRequest(long id_utente) {
        this.id_utente = id_utente;
    }

    public AggiornaTitolariRequest() {
    }

    @Override
    public long getId_utente() {
        return id_utente;
    }

    @Override
    public void setId_utente(long id_utente) {
        this.id_utente = id_utente;
    }
}
