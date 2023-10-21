package com.fanta.calcetto.services.serviceInterface;

import com.fanta.calcetto.entities.Giocatore;

import java.util.List;

public interface GiocatoreService {
    public List<Giocatore> findAll();
    public void putGiocatore(long costo,long maxid,String ruolo, String nomeGiocatore);
    public long getMax();
    public void inserisciValutazioneGiocatore(Giocatore giocatore, long valutazione);
}
