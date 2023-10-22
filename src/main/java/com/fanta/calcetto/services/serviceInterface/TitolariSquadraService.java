package com.fanta.calcetto.services.serviceInterface;

import com.fanta.calcetto.controllers.responses.FormazioneResponse;
import com.fanta.calcetto.entities.Giocatore;
import com.fanta.calcetto.entities.TitolariSquadra;
import com.fanta.calcetto.entities.Utente;

import java.util.List;
import java.util.Optional;

public interface TitolariSquadraService {

    public void saveTitolariSquadra(long id_utente, Giocatore giocatore);
    public FormazioneResponse getTitolari(long id);
    public List<TitolariSquadra> getTitolariSquadraById(long id);
    public TitolariSquadra getTitolariSquadraByIdSquadraAndIdGiocatore(long id_squadra, long id_giocatore);
    public void eliminaTitolari(List<TitolariSquadra> titolari);
    public void eliminaTitolare(TitolariSquadra titolare);
    public void inserisciFormazioneTitolare(long id);
}
