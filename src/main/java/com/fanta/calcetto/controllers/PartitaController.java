package com.fanta.calcetto.controllers;

import com.fanta.calcetto.controllers.requests.SaveUserRequest;
import com.fanta.calcetto.controllers.responses.FormazioneResponse;
import com.fanta.calcetto.controllers.responses.GiocatoriPartitaResponse;
import com.fanta.calcetto.controllers.responses.model.GiocatoriModel;
import com.fanta.calcetto.controllers.responses.model.TitolariModel;
import com.fanta.calcetto.entities.Giocatore;
import com.fanta.calcetto.entities.Partita;
import com.fanta.calcetto.entities.Squadra;
import com.fanta.calcetto.entities.Utente;
import com.fanta.calcetto.services.serviceInterface.GiornataService;
import com.fanta.calcetto.services.serviceInterface.PartitaService;
import com.fanta.calcetto.services.serviceInterface.SquadraService;
import com.fanta.calcetto.services.serviceInterface.TitolariSquadraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping("/matchs")
public class PartitaController {

    @Autowired
    public PartitaService partitaService;

    @Autowired
    public TitolariSquadraService titolariSquadraService;

    @Autowired
    public SquadraService squadraService;

    @Autowired
    public GiornataService giornataService;

    @GetMapping("/all")
    @ResponseBody
    public List<Partita> getAllMatchs() {
        return partitaService.findAll();
    }

    @PostMapping("/calcolaGiornata")
    @ResponseBody
    public void calcolaGiornata() {
        giornataService.calcolaGiornata();
    }

    @GetMapping("/giocatoriPartita/{id}")
    @ResponseBody
    public GiocatoriPartitaResponse getGiocatoriPartita(
            @PathVariable long id
    ) {
        FormazioneResponse formazioneResponse = titolariSquadraService.getTitolari(id);
        GiocatoriPartitaResponse giocatoriPartitaResponse = new GiocatoriPartitaResponse();

        List<GiocatoriModel> portieri = new ArrayList<>();
        List<GiocatoriModel> difensori = new ArrayList<>();
        List<GiocatoriModel> attaccanti = new ArrayList<>();
        List<GiocatoriModel> riserve = new ArrayList<>();

        Squadra squadra = squadraService.getSquadraByUserId(id);
        Set<Giocatore> giocatoriAcquistati  = squadra.getGiocatori_acquistati();

        for (Giocatore portiere: formazioneResponse.getPortieri()) {
            GiocatoriModel giocatore = new GiocatoriModel();
            giocatore.setNome(portiere.getNome());
            giocatore.setInfortunato_espulso(portiere.isBinfortunato() || portiere.isBsqualificato());
            giocatore.setValutazione(0);
            portieri.add(giocatore);

            List<Giocatore> giocatoriTrovatiTitolari = new ArrayList<>();
            for (Giocatore giocatoreAcquistato : giocatoriAcquistati) {
                if (giocatoreAcquistato.getId_giocatore() == portiere.getId_giocatore()) {
                    giocatoriTrovatiTitolari.add(giocatoreAcquistato);
                }
            }
            giocatoriTrovatiTitolari.forEach(giocatoriAcquistati::remove);
        }

        for (Giocatore difensore: formazioneResponse.getDifensori()) {
            GiocatoriModel giocatore = new GiocatoriModel();
            giocatore.setNome(difensore.getNome());
            giocatore.setInfortunato_espulso(difensore.isBinfortunato() || difensore.isBsqualificato());
            giocatore.setValutazione(0);
            difensori.add(giocatore);

            List<Giocatore> giocatoriTrovatiTitolari = new ArrayList<>();
            for (Giocatore giocatoreAcquistato : giocatoriAcquistati) {
                if (giocatoreAcquistato.getId_giocatore() == difensore.getId_giocatore()) {
                    giocatoriTrovatiTitolari.add(giocatoreAcquistato);
                }
            }
            giocatoriTrovatiTitolari.forEach(giocatoriAcquistati::remove);
        }

        for (Giocatore attaccante: formazioneResponse.getAttaccanti()) {
            GiocatoriModel giocatore = new GiocatoriModel();
            giocatore.setNome(attaccante.getNome());
            giocatore.setInfortunato_espulso(attaccante.isBinfortunato() || attaccante.isBsqualificato());
            giocatore.setValutazione(0);
            attaccanti.add(giocatore);

            List<Giocatore> giocatoriTrovatiTitolari = new ArrayList<>();
            for (Giocatore giocatoreAcquistato : giocatoriAcquistati) {
                if (giocatoreAcquistato.getId_giocatore() == attaccante.getId_giocatore()) {
                    giocatoriTrovatiTitolari.add(giocatoreAcquistato);
                }
            }
            giocatoriTrovatiTitolari.forEach(giocatoriAcquistati::remove);
        }

        TitolariModel titolari = new TitolariModel();

        titolari.setPortieri(portieri);
        titolari.setDifensori(difensori);
        titolari.setAttaccanti(attaccanti);

        giocatoriPartitaResponse.setTitolari(titolari);
        for (Giocatore giocatoreAcquistato : giocatoriAcquistati) {
            GiocatoriModel giocatore = new GiocatoriModel();
            giocatore.setNome(giocatoreAcquistato.getNome());
            giocatore.setInfortunato_espulso(giocatoreAcquistato.isBinfortunato() || giocatoreAcquistato.isBsqualificato());
            giocatore.setValutazione(0);
            riserve.add(giocatore);
        }
        giocatoriPartitaResponse.setRiserve(riserve);

        return giocatoriPartitaResponse;
    }
}
