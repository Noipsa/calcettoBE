package com.fanta.calcetto.services;

import com.fanta.calcetto.controllers.responses.FormazioneResponse;
import com.fanta.calcetto.entities.*;
import com.fanta.calcetto.repository.GiornataRepository;
import com.fanta.calcetto.repository.TitolariEffettiviGiornataRepository;
import com.fanta.calcetto.repository.TitolariSquadraRepository;
import com.fanta.calcetto.services.serviceInterface.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TitolariSquadraServiceImpl implements TitolariSquadraService {

    @Autowired
    private TitolariSquadraRepository titolariSquadraRepository;

    @Autowired
    private SquadraService squadraService;

    @Autowired
    private RosaService rosaService;

    @Autowired
    private UtentiService utentiService;

    @Autowired
    private GiornataRepository giornataRepository;

    @Autowired
    public TitolariEffettiviGiornataRepository titolariEffettiviGiornataRepository;

    @Override
    public void saveTitolariSquadra(long id_utente, Giocatore titolare) {

        Utente utente = utentiService.getUserById(id_utente).get();
        long id_squadra = utente.getId_squadra();

        Optional<Squadra> squadra = squadraService.getSquadraById(id_squadra);

        Set<Giocatore> giocatore = squadra.get().getGiocatori_acquistati();

        Giocatore giocatoreTrovato = giocatore.stream().filter((g -> Objects.equals(g.getId_giocatore(), titolare.getId_giocatore()))).findAny().get();

        Optional<TitolariSquadra> giocatoreGiaTitolare = titolariSquadraRepository.findByIdAndGiocatori(squadra.get().getId_squadra(), giocatoreTrovato.getId_giocatore());

        if (giocatoreGiaTitolare.isEmpty()) {
            List<TitolariSquadra> titolariSquadraGiaPresenti = titolariSquadraRepository.findAllByIdSquadraTitolare(squadra.get().getId_squadra());

            TitolariSquadra titolariSquadra = new TitolariSquadra();
            titolariSquadra.setId_giocatore(giocatoreTrovato.getId_giocatore());
            titolariSquadra.setId_squadra(squadra.get().getId_squadra());

            titolariSquadraRepository.save(titolariSquadra);
        }
    }

    @Override
    public FormazioneResponse getTitolari(long id) {

        Utente utente = utentiService.getUserById(id).get();
        long id_squadra = utente.getId_squadra();

        Squadra squadra = squadraService.getSquadraById(id_squadra).get();
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
    public TitolariSquadra getTitolariSquadraByIdSquadraAndIdGiocatore(long id_squadra, long id_giocatore) {
        Optional<TitolariSquadra> titolariSquadra =  titolariSquadraRepository.findByIdAndGiocatori(id_squadra, id_giocatore);
        return titolariSquadra.orElse(null);
    }

    @Override
    public void eliminaTitolari(List<TitolariSquadra> titolari) {
        titolariSquadraRepository.deleteAll(titolari);
    }

    @Override
    public void eliminaTitolare(TitolariSquadra titolare) {
        titolariSquadraRepository.delete(titolare);
    }

    @Override
    @Transactional
    public void inserisciFormazioneTitolare(long id) {
        Utente utente = utentiService.getUserById(id).get();
        long id_squadra = utente.getId_squadra();
        long giornata = giornataRepository.getMax();

        titolariEffettiviGiornataRepository.deleteAllByIdSquadraAndGiornata(id_squadra, giornata);

        List<TitolariSquadra> titolariSquadra = titolariSquadraRepository.findAllByIdSquadraTitolare(id_squadra);
        System.out.println(titolariSquadra);
        for (TitolariSquadra titolare : titolariSquadra) {
            TitolariEffettiviGiornata titolareEffettiviGiornata = new TitolariEffettiviGiornata();
            titolareEffettiviGiornata.setId_giocatore(titolare.getId_giocatore());
            titolareEffettiviGiornata.setId_squadra(id_squadra);
            titolareEffettiviGiornata.setGiornata(giornataRepository.getMax());
            titolariEffettiviGiornataRepository.save(titolareEffettiviGiornata);
        }

    }

    private List<Giocatore> trovaGiocatore(List<TitolariSquadra> titolari, Set<Giocatore> giocatoriAcquistati, String ruolo) {
        List<Giocatore> giocatori = new ArrayList<>();
        for (TitolariSquadra titolare : titolari) {
            giocatori.addAll(giocatoriAcquistati.stream().filter(giocatore ->
                    Objects.equals(giocatore.getId_giocatore(), titolare.getId_giocatore()) &&
                    giocatore.getEruolo().equals(ruolo)).toList());

        }
        return giocatori;
    }
}
