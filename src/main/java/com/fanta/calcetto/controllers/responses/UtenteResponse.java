package com.fanta.calcetto.controllers.responses;

import com.fanta.calcetto.entities.Squadra;
import com.fanta.calcetto.entities.Utente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class UtenteResponse {
    private Utente utente;
    private Squadra squadra;

    public UtenteResponse() {
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Squadra getSquadra() {
        return squadra;
    }

    public void setSquadra(Squadra squadra) {
        this.squadra = squadra;
    }
}
