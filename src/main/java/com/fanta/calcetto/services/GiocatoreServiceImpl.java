package com.fanta.calcetto.services;

import com.fanta.calcetto.entities.Giocatore;
import com.fanta.calcetto.entities.Giornata;
import com.fanta.calcetto.entities.ValutazionePartita;
import com.fanta.calcetto.repository.GiocatoreRepository;
import com.fanta.calcetto.repository.GiornataRepository;
import com.fanta.calcetto.repository.PartitaRepository;
import com.fanta.calcetto.repository.ValutazionePartitaRepository;
import com.fanta.calcetto.services.serviceInterface.GiocatoreService;
import com.fanta.calcetto.services.serviceInterface.GiornataService;
import com.fanta.calcetto.services.serviceInterface.ValutazionePartitaService;
import jakarta.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class GiocatoreServiceImpl implements GiocatoreService {

    @Autowired
    private GiocatoreRepository giocatoreRepository;

    @Autowired
    private GiornataRepository giornataRepository;

    @Autowired
    private ValutazionePartitaRepository valutazionePartitaRepository;

    @Override
    public List<Giocatore> findAll() {
        return giocatoreRepository.findAll();
    }

    @Override
    public void putGiocatore(long costo,long maxId,String ruolo, String nomeGiocatore) {
        giocatoreRepository.putGiocatore(costo, maxId,ruolo, nomeGiocatore);
    }

    @Override
    public long getMax() {
        return giocatoreRepository.getMax();
    }

    @Override
    public void inserisciValutazioneGiocatore(Giocatore giocatore, long valutazione) {
        if (valutazionePartitaRepository.getValutazioneByIdGiocatore(giocatore.getId_giocatore()).isEmpty()) {
            long giornata = giornataRepository.getMax();
            ValutazionePartita valutazionePartita = new ValutazionePartita();

            valutazionePartita.setValutazione(valutazione);
            valutazionePartita.setId_giornata(giornata);
            valutazionePartita.setId_giocatore(giocatore.getId_giocatore());

            valutazionePartitaRepository.save(valutazionePartita);
        }
    }


}
