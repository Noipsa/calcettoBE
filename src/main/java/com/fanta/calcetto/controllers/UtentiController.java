package com.fanta.calcetto.controllers;

import com.fanta.calcetto.controllers.requests.LoginRequest;
import com.fanta.calcetto.controllers.requests.SaveUserRequest;
import com.fanta.calcetto.controllers.responses.UtenteResponse;
import com.fanta.calcetto.entities.Giocatore;
import com.fanta.calcetto.entities.Squadra;
import com.fanta.calcetto.entities.Utente;
import com.fanta.calcetto.services.serviceInterface.SquadraService;
import com.fanta.calcetto.services.serviceInterface.UtentiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/utenti")
public class UtentiController {

    @Autowired
    public UtentiService utentiService;

    @Autowired
    public SquadraService squadraService;

    @PostMapping("/save")
    @ResponseBody
    public void saveUser(
            @RequestBody SaveUserRequest request
            ) {
        Objects.requireNonNull(request.getNome_squadra());
        Objects.requireNonNull(request.getPassword());
        Objects.requireNonNull(request.getEmail());

        //Controllo prima se esiste a DB
        Utente utente = new Utente();
        utente.setEmail(request.getEmail());
        utente.setPassword(request.getPassword());
        List<Utente> exist = utentiService.getUsers(utente);

        if (!exist.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,  "Utente gia registrato");
        }

        Squadra squadra = squadraService.addSquadra(request.getNome_squadra());

        utente.setId_squadra(squadra);
        utente.setBactive(false);
        utentiService.addUser(utente);
    }

    @PostMapping("/login")
    @ResponseBody
    public Utente loginUser(
            @RequestBody LoginRequest request
    ) {
        Objects.requireNonNull(request.getPassword());
        Objects.requireNonNull(request.getEmail());

        //Controllo prima se esiste a DB
        Utente utente = new Utente();
        utente.setEmail(request.getEmail());
        utente.setPassword(request.getPassword());
        List<Utente> utenti = utentiService.getUsers(utente);

        if (utenti.size() == 1) {
            for (Utente u : utenti) {
                if (!u.isBactive())
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,  "Utente gia registrato");

                return u;
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,  "Utente gia registrato");
        }

        return null;
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    public UtenteResponse findUser(@PathVariable long id) {

        Utente utente = utentiService.getUserById(id).get();
        Squadra squadra = squadraService.getSquadraByUserId(utente.getId_utente());
        utente.setId_squadra(squadra);

        UtenteResponse utenteResponse = new UtenteResponse();
        utenteResponse.setUtente(utente);
        utenteResponse.setSquadra(squadra);
        return utenteResponse;
    }
}
