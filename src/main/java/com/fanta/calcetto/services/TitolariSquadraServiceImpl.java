package com.fanta.calcetto.services;

import com.fanta.calcetto.controllers.responses.FormazioneResponse;
import com.fanta.calcetto.entities.*;
import com.fanta.calcetto.repository.TitolariSquadraRepository;
import com.fanta.calcetto.services.serviceInterface.RosaService;
import com.fanta.calcetto.services.serviceInterface.SquadraService;
import com.fanta.calcetto.services.serviceInterface.TitolariSquadraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TitolariSquadraServiceImpl implements TitolariSquadraService {

    @Autowired
    public TitolariSquadraRepository titolariSquadraRepository;

    @Autowired
    public SquadraService squadraService;

    @Autowired
    public RosaService rosaService;

    @Override
    public void saveTitolariSquadra(Utente utente, Giocatore titolare) {

        Squadra squadra = squadraService.getSquadraByUserId(utente.getId_utente());

        Set<Giocatore> giocatore = squadra.getGiocatori_acquistati();

        Giocatore giocatoreTrovato = giocatore.stream().filter((g -> g.getId_giocatore() == titolare.getId_giocatore())).findAny().get();

        Optional<TitolariSquadra> giocatoreGiaTitolare = titolariSquadraRepository.findByIdAndGiocatori(squadra.getId_squadra(), giocatoreTrovato.getId_giocatore());

        if (giocatoreGiaTitolare.isEmpty()) {
            List<TitolariSquadra> titolariSquadraGiaPresenti = titolariSquadraRepository.findAllByIdSquadraTitolare(squadra.getId_squadra());
            giocatoreTrovato.setId_titolari_squadra(titolariSquadraGiaPresenti);

            TitolariSquadra titolariSquadra = new TitolariSquadra();
            titolariSquadra.setGiocatori_titolari(giocatoreTrovato);
            titolariSquadra.setId_squadra(squadra.getId_squadra());

            titolariSquadraRepository.save(titolariSquadra);
        }
    }

    @Override
    public FormazioneResponse getTitolari(long id) {

        Squadra squadra = squadraService.getSquadraByUserId(id);
        Rosa rosa = rosaService.getRosaById(squadra.getId_formazione());

        List<TitolariSquadra> titolari = titolariSquadraRepository.findAllByIdSquadraTitolare(squadra.getId_squadra());
        FormazioneResponse formazione = new FormazioneResponse();


        List<Giocatore> portieri = new ArrayList<>();
        if (titolari.isEmpty() || squadra.getGiocatori_acquistati().isEmpty()) {
            portieri.add(new Giocatore());
        } else {
            List<Giocatore> portiereTitolare = trovaGiocatore(titolari, squadra.getGiocatori_acquistati(), "POR");
            if (portiereTitolare.isEmpty()) {
                portieri.add(new Giocatore());
            } else {
                for (Giocatore portiere : portiereTitolare) {
                    portieri.add(portiere);
                }
            }
        }
        formazione.setPortieri(portieri);

        List<Giocatore> difensori = new ArrayList<>();
        if (!titolari.isEmpty() || !squadra.getGiocatori_acquistati().isEmpty()) {
            List<Giocatore> difensoriTitolare = trovaGiocatore(titolari, squadra.getGiocatori_acquistati(), "DIF");
            if (!difensoriTitolare.isEmpty()) {
                for (Giocatore difensore : difensoriTitolare) {
                    difensori.add(difensore);
                }
            }
        }

        if (difensori.size() < rosa.getNumero_difensori()) {
            int numeroDifensori = rosa.getNumero_difensori() - difensori.size();
            for (int i = 0; i < numeroDifensori; i++) {
                difensori.add(new Giocatore());
            }
        }
        formazione.setDifensori(difensori);

        List<Giocatore> attaccanti = new ArrayList<>();
        if (!titolari.isEmpty() || !squadra.getGiocatori_acquistati().isEmpty()) {
            List<Giocatore> attaccantiTitolare = trovaGiocatore(titolari, squadra.getGiocatori_acquistati(), "ATT");
            if (!attaccantiTitolare.isEmpty()) {
                for (Giocatore attaccante : attaccantiTitolare) {
                    attaccanti.add(attaccante);
                }
            }
        }

        if (attaccanti.size() < rosa.getNumero_attaccanti()) {
            int numeroAttacanti = rosa.getNumero_attaccanti() - attaccanti.size();
            for (int i = 0; i < numeroAttacanti; i++) {
                attaccanti.add(new Giocatore());
            }
        }
        formazione.setAttaccanti(attaccanti);

        return formazione;
    }

    @Override
    public List<TitolariSquadra> getTitolariSquadraById(long id) {
        return titolariSquadraRepository.findAllByIdSquadraTitolare(id);
    }

    @Override
    public void eliminaTitolari(List<TitolariSquadra> titolari) {
        titolariSquadraRepository.deleteAll(titolari);
    }

    private List<Giocatore> trovaGiocatore(List<TitolariSquadra> titolari, Set<Giocatore> giocatoriAcquistati, String ruolo) {
        List<Giocatore> giocatori = new ArrayList<>();
        for (TitolariSquadra titolare : titolari) {
            giocatori.addAll(giocatoriAcquistati.stream().filter(giocatore ->
                    giocatore.getId_giocatore() == titolare.getGiocatori_titolari().getId_giocatore() &&
                    giocatore.getEruolo().equals(ruolo)).toList());

        }
        return giocatori;
    }
}
