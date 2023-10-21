package com.fanta.calcetto.controllers.requests;

import com.fanta.calcetto.entities.Rosa;
import com.fanta.calcetto.entities.Utente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class GiocatoriRequest {
    private int type;
    private Utente utente;

    public GiocatoriRequest() {
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }
}
