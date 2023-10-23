package com.fanta.calcetto.controllers.requests;

import com.fanta.calcetto.entities.Rosa;

public class AggiornaRiserveRequest {
    private Rosa rosa;
    private long id_utente;

    @Override
    public String toString() {
        return "AggiornaRiserveRequest{" +
                "rosa=" + rosa +
                ", id_utente=" + id_utente +
                '}';
    }

    public AggiornaRiserveRequest() {
    }

    public AggiornaRiserveRequest(Rosa rosa) {
        this.rosa = rosa;
    }

    public Rosa getRosa() {
        return rosa;
    }

    public void setRosa(Rosa rosa) {
        this.rosa = rosa;
    }

    public long getId_utente() {
        return id_utente;
    }

    public void setId_utente(long id_utente) {
        this.id_utente = id_utente;
    }
}
