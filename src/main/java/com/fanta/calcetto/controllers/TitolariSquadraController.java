package com.fanta.calcetto.controllers;

import com.fanta.calcetto.controllers.requests.AcquistoGiocatoriRequest;
import com.fanta.calcetto.controllers.requests.AggiornaTitolariRequest;
import com.fanta.calcetto.controllers.responses.FormazioneResponse;
import com.fanta.calcetto.entities.Giocatore;
import com.fanta.calcetto.entities.Squadra;
import com.fanta.calcetto.entities.Utente;
import com.fanta.calcetto.services.serviceInterface.TitolariSquadraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/titolari")
public class TitolariSquadraController {

    @Autowired
    public TitolariSquadraService titolariSquadraService;

    @PostMapping("/save")
    @ResponseBody
    public void saveTitolare(
            @RequestBody AggiornaTitolariRequest request
    ) {
        Objects.requireNonNull(request.getUtente());
        Objects.requireNonNull(request.getGiocatore());

        Utente utente = request.getUtente();
        Giocatore giocatore = request.getGiocatore();

        titolariSquadraService.saveTitolariSquadra(utente, giocatore);
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public FormazioneResponse saveTitolare(
            @PathVariable long id
    ) {
        return titolariSquadraService.getTitolari(id);
    }

}
