package com.fanta.calcetto.services;

import com.fanta.calcetto.controllers.responses.ValutazioneMatchsResponse;
import com.fanta.calcetto.entities.Giocatore;
import com.fanta.calcetto.entities.ValutazionePartita;
import com.fanta.calcetto.repository.GiocatoreRepository;
import com.fanta.calcetto.repository.ValutazionePartitaRepository;
import com.fanta.calcetto.services.serviceInterface.ValutazionePartitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ValutazionePartitaServiceImpl implements ValutazionePartitaService {
    @Autowired
    private ValutazionePartitaRepository valutazionePartitaRepository;

    @Autowired
    private GiocatoreRepository giocatoreRepository;
    @Override
    public List<ValutazionePartita> getValutazioneByMinIdGiornataAndIdGiocatore(long idGiornata, long idGiocatore) {
        return valutazionePartitaRepository.getValutazioneByMinIdGiornataAndIdGiocatore(idGiornata, idGiocatore);
    }

    @Override
    public ValutazionePartita getValutazioneByIdGiornataAndIdGiocatore(long idGiornata, long idGiocatore) {
        return valutazionePartitaRepository.getValutazioneByIdGiornataAndIdGiocatore(idGiornata, idGiocatore);
    }

    @Override
    public List<ValutazionePartita> getValutazioneByIdGiocatore(long idGiornata) {
        return valutazionePartitaRepository.getValutazioneByIdGiocatore(idGiornata);
    }

    @Override
    public List<ValutazioneMatchsResponse> getValutazioniMatchs(long idSquadra, long idGiornata) {
        List<Giocatore> giocatoriSquadra = giocatoreRepository.getGiocatoriByIdSquadraUfficiale(idSquadra);

        List<ValutazioneMatchsResponse> valutazionePartita = new ArrayList<>();
        for (Giocatore giocatore : giocatoriSquadra) {
            ValutazionePartita valutazioneGiocatore = valutazionePartitaRepository.getValutazioneByIdGiornataAndIdGiocatore(idGiornata, giocatore.getId_giocatore());
            ValutazioneMatchsResponse valutazione = new ValutazioneMatchsResponse();
            valutazione.setNome(giocatore.getNome());

            if (valutazioneGiocatore != null) {
                valutazione.setValutazione(valutazioneGiocatore.getValutazione());
            } else {
                valutazione.setValutazione(-1);
            }
            valutazionePartita.add(valutazione);
        }

        return valutazionePartita;
    }
}
