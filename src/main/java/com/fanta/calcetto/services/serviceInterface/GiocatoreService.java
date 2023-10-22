package com.fanta.calcetto.services.serviceInterface;

import com.fanta.calcetto.entities.Giocatore;

import java.util.List;

public interface GiocatoreService {
    public List<Giocatore> findAll();
    public void putGiocatore(long costo,String ruolo, String nomeGiocatore, long id_squadra_ufficiale);
    public long getMax();
    public void inserisciValutazioneGiocatore(Giocatore giocatore, long valutazione);
    public void squalificaGiocatore(long id);
    public void infortunioGiocatore(long id);
    public void eliminaGiocatore(long id);
}
