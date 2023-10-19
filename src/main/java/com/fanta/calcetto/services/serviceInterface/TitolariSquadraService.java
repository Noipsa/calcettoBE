package com.fanta.calcetto.services.serviceInterface;

import com.fanta.calcetto.controllers.responses.FormazioneResponse;
import com.fanta.calcetto.entities.Giocatore;
import com.fanta.calcetto.entities.TitolariSquadra;
import com.fanta.calcetto.entities.Utente;

import java.util.List;

public interface TitolariSquadraService {

    public void saveTitolariSquadra(Utente utente, Giocatore giocatore);

    public FormazioneResponse getTitolari(long id);

    public List<TitolariSquadra> getTitolariSquadraById(long id);

    public void eliminaTitolari(List<TitolariSquadra> titolari);
}
