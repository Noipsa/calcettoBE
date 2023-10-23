package com.fanta.calcetto.controllers.requests;

import com.fanta.calcetto.entities.Giocatore;

public class SaveRiserveRequest{
    private long ordine_entrata;
    private Giocatore giocatore;
    private long id_utente;

    @Override
    public String toString() {
        return "SaveRiserveRequest{" +
                "ordine_entrata=" + ordine_entrata +
                ", giocatore=" + giocatore +
                ", id_utente=" + id_utente +
                '}';
    }

    public SaveRiserveRequest() {
    }

    public long getOrdine_entrata() {
        return ordine_entrata;
    }

    public void setOrdine_entrata(long ordine_entrata) {
        this.ordine_entrata = ordine_entrata;
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
