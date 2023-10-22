package com.fanta.calcetto.controllers.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class InserisciGiocatoreRequest {
    private String nomeGiocatore;
    private long valutazione;
    private String ruolo;
    private long id_squadra_ufficiale;


    @Override
    public String toString() {
        return "InserisciGiocatoreRequest{" +
                "nomeGiocatore='" + nomeGiocatore + '\'' +
                ", valutazione=" + valutazione +
                ", ruolo='" + ruolo + '\'' +
                ", id_squadra_ufficiale=" + id_squadra_ufficiale +
                '}';
    }

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

    public long getId_squadra_ufficiale() {
        return id_squadra_ufficiale;
    }

    public void setId_squadra_ufficiale(long id_squadra_ufficiale) {
        this.id_squadra_ufficiale = id_squadra_ufficiale;
    }
}
