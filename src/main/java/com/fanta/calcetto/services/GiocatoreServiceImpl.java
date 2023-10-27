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
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public void putGiocatore(long costo,String ruolo, String nomeGiocatore, long id_squadra_ufficiale) {
        Giocatore giocatore = new Giocatore();
        giocatore.setBinfortunato(false);
        giocatore.setBsqualificato(false);
        giocatore.setCosto(costo);
        giocatore.setEruolo(ruolo);
        giocatore.setNome(nomeGiocatore);
        giocatore.setId_squadra_ufficiale(id_squadra_ufficiale);
        giocatoreRepository.save(giocatore);
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

    public Giocatore getGiocatoreById(long id) {
        return giocatoreRepository.findById(id).get();
    }

    @Override
    public void squalificaGiocatore(long id) {
        Giocatore giocatore = getGiocatoreById(id);
        giocatore.setBsqualificato(!giocatore.isBsqualificato());
        giocatoreRepository.save(giocatore);
    }

    @Override
    public void infortunioGiocatore(long id) {
        Giocatore giocatore = getGiocatoreById(id);
        giocatore.setBinfortunato(!giocatore.isBinfortunato());
        giocatoreRepository.save(giocatore);
    }

    @Override
    public void eliminaGiocatore(long id) {
        Giocatore giocatore = getGiocatoreById(id);
        giocatoreRepository.delete(giocatore);
    }

    @Override
    @Transactional
    public void insertMassivo(Map<String, String> giocatori_valutazione) {
        List<ValutazionePartita> valutazionePartite = new ArrayList<>();
        List<Giocatore> giocatori = giocatoreRepository.findAll();

        for (Giocatore giocatore : giocatori) {
            String valutazione = giocatori_valutazione.get(giocatore.getId_giocatore().toString());
            if (valutazione != null && !valutazione.trim().equals("-1")) {
                ValutazionePartita valutazionePartita = new ValutazionePartita();
                valutazionePartita.setId_giocatore(giocatore.getId_giocatore());
                valutazionePartita.setId_giornata(giornataRepository.getMax());
                valutazionePartita.setValutazione(Long.parseLong(valutazione));
                valutazionePartite.add(valutazionePartita);

                valutazionePartitaRepository.deleteByIdGiocatoreAndGiornata(giocatore.getId_giocatore(), giocatoreRepository.getMax());
            }
        }

        valutazionePartitaRepository.saveAll(valutazionePartite);
    }


}
