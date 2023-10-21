package com.fanta.calcetto.controllers.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class InserisciGiocatoreRequest {
    String nomeGiocatore;
    long valutazione;
    String ruolo;

    public InserisciGiocatoreRequest() {
    }

    public String getNomeGiocatore() {
        return nomeGiocatore;
    }

    public void setNomeGiocatore(String nomeGiocatore) {
        this.nomeGiocatore = nomeGiocatore;
    }

    public long getValutazione() {
        return valutazione;
    }

    public void setValutazione(long valutazione) {
        this.valutazione = valutazione;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }
}
