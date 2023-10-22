package com.fanta.calcetto.controllers.responses;

import com.fanta.calcetto.entities.Utente;

public class LoginResponse {
    private Utente utente;
    private boolean Mercato;

    @Override
    public String toString() {
        return "LoginResponse{" +
                "utente=" + utente +
                ", Mercato=" + Mercato +
                '}';
    }

    public LoginResponse() {
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public boolean isMercato() {
        return Mercato;
    }

    public void setMercato(boolean mercato) {
        Mercato = mercato;
    }
}
