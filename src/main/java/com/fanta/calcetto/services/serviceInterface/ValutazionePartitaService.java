package com.fanta.calcetto.services.serviceInterface;

import com.fanta.calcetto.controllers.responses.ValutazioneMatchsResponse;
import com.fanta.calcetto.entities.ValutazionePartita;

import java.util.List;

public interface ValutazionePartitaService {


    public List<ValutazionePartita> getValutazioneByMinIdGiornataAndIdGiocatore(long idGiornata, long idGiocatore);

    public ValutazionePartita getValutazioneByIdGiornataAndIdGiocatore(long idGiornata, long idGiocatore);

    public List<ValutazionePartita> getValutazioneByIdGiocatore(long idGiornata);

    public List<ValutazioneMatchsResponse> getValutazioniMatchs(long idSquadra, long idGiornata);
}
