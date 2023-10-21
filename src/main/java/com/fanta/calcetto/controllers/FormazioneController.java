package com.fanta.calcetto.controllers;

import com.fanta.calcetto.controllers.requests.AggiornaFormazioneRequest;
import com.fanta.calcetto.controllers.requests.GiocatoriRequest;
import com.fanta.calcetto.controllers.responses.ClassificaResponse;
import com.fanta.calcetto.controllers.responses.FormazioneResponse;
import com.fanta.calcetto.entities.*;
import com.fanta.calcetto.services.serviceInterface.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/formation")
public class FormazioneController {

    @Autowired
    public RosaService rosaService;

    @Autowired
    public SquadraService squadraService;

    @Autowired
    public UtentiService utentiService;

    @Autowired
    public TitolariSquadraService titolariSquadraService;

    @Autowired
    public ValutazionePartitaService valutazionePartitaService;

    @Autowired
    public PartitaService partitaService;

    @GetMapping("/all")
    @ResponseBody
    public List<Rosa> getAllRose() {
        return rosaService.getAllRose();
    }

    @PostMapping("/aggiornaFormazione")
    @ResponseBody
    public FormazioneResponse putFormazione(
            @RequestBody AggiornaFormazioneRequest request
    ) {
        Objects.requireNonNull(request.getUtente());
        Objects.requireNonNull(request.getRosa());

        Rosa rosa = request.getRosa();
        Utente utente = request.getUtente();
        Squadra squadra = squadraService.getSquadraByUserId(utente.getId_utente());

        if (squadra.getId_formazione() != rosa.getId_formazione()) {
            squadra.setId_formazione(rosa.getId_formazione());

            squadraService.putSquadra(squadra);

            List<TitolariSquadra> titolariSquadra = titolariSquadraService.getTitolariSquadraById(utente.getId_utente());
            titolariSquadraService.eliminaTitolari(titolariSquadra);
        }



        FormazioneResponse formazione = new FormazioneResponse();

        List<Giocatore> portieri = new ArrayList<>();
        portieri.add(new Giocatore());
        formazione.setPortieri(portieri);

        List<Giocatore> difensori = new ArrayList<>();
        for (int i = 0; i < rosa.getNumero_difensori(); i++) {
            difensori.add(new Giocatore());
        }
        formazione.setDifensori(difensori);

        List<Giocatore> attaccanti = new ArrayList<>();
        for (int i = 0; i < rosa.getNumero_attaccanti(); i++) {
            attaccanti.add(new Giocatore());
        }
        formazione.setAttaccanti(attaccanti);

        return formazione;
    }

    @PostMapping("/getGiocatori")
    @ResponseBody
    public List<Giocatore> getGiocatori(
            @RequestBody GiocatoriRequest request
    ) {
        Objects.requireNonNull(request.getUtente());

        int type = request.getType();
        //per aver i giocatori aggiornati;

        Squadra squadra = squadraService.getSquadraByUserId(request.getUtente().getId_utente());

        Set<Giocatore> giocatoreAcquistati = squadra.getGiocatori_acquistati();

        switch (type) {
            case 1:
                return getGiocatoreByRuolo(giocatoreAcquistati, "POR");
            case 2:
                return getGiocatoreByRuolo(giocatoreAcquistati, "DIF");
            case 3:
                return getGiocatoreByRuolo(giocatoreAcquistati, "ATT");
            default:
                return null;
        }
    }

    private List<Giocatore> getGiocatoreByRuolo(Set<Giocatore> giocatoreAcquistati, String filter) {
        return giocatoreAcquistati.stream().filter((giocatore -> giocatore.getEruolo().equals(filter))).toList();
    }

    @GetMapping("/getGiocatoriPosseduti/{id}")
    @ResponseBody
    public Set<Giocatore> getGiocatoriPosseduti(
            @PathVariable long id
    ) {
        Utente utente = utentiService.getUserById(id).get();
        Squadra squadra = utente.getId_squadra();
        return squadra.getGiocatori_acquistati();
    }

    @GetMapping("/classifica")
    @ResponseBody
    public List<ClassificaResponse> getClassifica() {
        List<ClassificaResponse> classificaResponses = new ArrayList<>();
        long comparatore = -1;
        List<Squadra> squadre = squadraService.findAll();
        long giornataAttuale = partitaService.getGiornataAttuale();
        for (Squadra squadra : squadre) {
            long sommaPunteggio = 0;
            List<TitolariSquadra> titolariSquadra = titolariSquadraService.getTitolariSquadraById(squadra.getId_squadra());
            for (TitolariSquadra giocatore : titolariSquadra) {
                long id_titolare = giocatore.getGiocatori_titolari().getId_giocatore();
                List<ValutazionePartita> valutazionePartite = valutazionePartitaService.getValutazioneByMinIdGiornataAndIdGiocatore(giornataAttuale, id_titolare);

                for (ValutazionePartita valutazione: valutazionePartite) {
                    sommaPunteggio += valutazione.getValutazione();
                }

            }
            ClassificaResponse classificaResponse = new ClassificaResponse();
            classificaResponse.setPunteggio(sommaPunteggio);
            classificaResponse.setNome(squadra.getNome_squadra());
            classificaResponse.setIdSquadra(squadra.getId_squadra());
            if (comparatore == -1) {
                comparatore = sommaPunteggio;
                classificaResponses.add(classificaResponse);
            } else if (comparatore != -1 && comparatore < sommaPunteggio) {
                comparatore = sommaPunteggio;
                classificaResponses.add(0, classificaResponse);
            } else if (comparatore != -1 && comparatore > sommaPunteggio) {
                comparatore = sommaPunteggio;
                classificaResponses.add( classificaResponse);
            }
        }

        return classificaResponses;
    }
}
