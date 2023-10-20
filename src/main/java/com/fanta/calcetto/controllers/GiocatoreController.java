package com.fanta.calcetto.controllers;

import com.fanta.calcetto.controllers.requests.AcquistoGiocatoriRequest;
import com.fanta.calcetto.controllers.requests.AggiornaFormazioneRequest;
import com.fanta.calcetto.controllers.requests.EliminaGiocatoreRequest;
import com.fanta.calcetto.entities.Giocatore;
import com.fanta.calcetto.entities.Partita;
import com.fanta.calcetto.entities.Squadra;
import com.fanta.calcetto.entities.Utente;
import com.fanta.calcetto.services.serviceInterface.GiocatoreService;
import com.fanta.calcetto.services.serviceInterface.PartitaService;
import com.fanta.calcetto.services.serviceInterface.SquadraService;
import com.fanta.calcetto.services.serviceInterface.UtentiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/giocatore")
public class GiocatoreController {
    @Autowired
    public GiocatoreService giocatoreService;

    @Autowired
    public SquadraService squadraService;

    @Autowired
    public UtentiService utenteService;

    @GetMapping("/all")
    @ResponseBody
    public List<Giocatore> getAllGiocatori() {
        return giocatoreService.findAll();
    }

    @PostMapping("/buy")
    @ResponseBody
    public Set<Giocatore> buyGiocatori(
            @RequestBody AcquistoGiocatoriRequest request
    ) throws Exception {
        Objects.requireNonNull(request.getUtente());
        Objects.requireNonNull(request.getGiocatore());

        Giocatore giocatore = request.getGiocatore();

        //per aver i giocatori aggiornati
        Optional<Utente> utente = utenteService.getUserById(request.getUtente().getId_utente());

        Squadra squadra = utente.get().getId_squadra();

        Set<Giocatore> giocatoriSquadra = squadra.getGiocatori_acquistati();

        List<Giocatore> giocatoreGiaPresente = giocatoriSquadra.stream().filter((g -> g.getId_giocatore() == giocatore.getId_giocatore())).toList();
        if (!giocatoreGiaPresente.isEmpty()) {
            throw new Exception("Giocatore gia Presente");
        } else if (giocatoriSquadra.size() == 12) {
            throw new Exception("Massimo Numero Giocatori");
        }

        giocatoriSquadra.add(giocatore);
        squadra.setCrediti_residui(squadra.getCrediti_residui() - giocatore.getCosto());

        squadra.setNumero_giocatori_acquistati(squadra.getNumero_giocatori_acquistati() + 1);
        squadra.setGiocatori_acquistati(giocatoriSquadra);
        squadraService.putSquadra(squadra);

        return giocatoriSquadra;
    }

    @PutMapping("/eliminaTitolare/{id}")
    @ResponseBody
    public void deleteGiocatori(
            @RequestBody EliminaGiocatoreRequest request
    ) {;

        Giocatore giocatore = request.getGiocatore();

        //per aver i giocatori aggiornati
        Optional<Utente> utente = utenteService.getUserById(request.getId_utente());

        Squadra squadra = utente.get().getId_squadra();

        Set<Giocatore> giocatoriSquadra = squadra.getGiocatori_acquistati();

        List<Giocatore> giocatoreGiaPresente = giocatoriSquadra.stream().filter((g -> g.getId_giocatore() == giocatore.getId_giocatore())).toList();
        if (!giocatoreGiaPresente.isEmpty()) {
            giocatoriSquadra.remove(giocatoreGiaPresente.get(0));
            squadra.setCrediti_residui(squadra.getCrediti_residui() + giocatore.getCosto());

            squadra.setNumero_giocatori_acquistati(squadra.getNumero_giocatori_acquistati() - 1);

            squadra.setGiocatori_acquistati(giocatoriSquadra);
            squadraService.putSquadra(squadra);
        }
    }

    @PostMapping("/sell")
    @ResponseBody
    public Set<Giocatore> sellGiocatori(
            @RequestBody AcquistoGiocatoriRequest request
    ) throws Exception {
        Objects.requireNonNull(request.getUtente());
        Objects.requireNonNull(request.getGiocatore());

        Giocatore giocatore = request.getGiocatore();

        //per aver i giocatori aggiornati
        Optional<Utente> utente = utenteService.getUserById(request.getUtente().getId_utente());

        Squadra squadra = utente.get().getId_squadra();

        Set<Giocatore> giocatoriSquadra = squadra.getGiocatori_acquistati();

        List<Giocatore> giocatoreGiaPresente = giocatoriSquadra.stream().filter((g -> g.getId_giocatore() == giocatore.getId_giocatore())).toList();
        if (!giocatoreGiaPresente.isEmpty()) {
            giocatoriSquadra.remove(giocatoreGiaPresente.get(0));
            squadra.setCrediti_residui(squadra.getCrediti_residui() + giocatore.getCosto());

            squadra.setNumero_giocatori_acquistati(squadra.getNumero_giocatori_acquistati() - 1);

            squadra.setGiocatori_acquistati(giocatoriSquadra);
            squadraService.putSquadra(squadra);
        }



        return giocatoriSquadra;
    }
}
