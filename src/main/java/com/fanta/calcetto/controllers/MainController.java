package com.fanta.calcetto.controllers;

import com.fanta.calcetto.controllers.requests.*;
import com.fanta.calcetto.controllers.responses.ClassificaResponse;
import com.fanta.calcetto.controllers.responses.FormazioneResponse;
import com.fanta.calcetto.controllers.responses.GiocatoriPartitaResponse;
import com.fanta.calcetto.controllers.responses.UtenteResponse;
import com.fanta.calcetto.controllers.responses.model.GiocatoriModel;
import com.fanta.calcetto.controllers.responses.model.TitolariModel;
import com.fanta.calcetto.entities.*;
import com.fanta.calcetto.services.serviceInterface.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@RestController
public class MainController {
    @Autowired
    public GiocatoreService giocatoreService;

    @Autowired
    public SquadraService squadraService;

    @Autowired
    public TitolariSquadraService titolariSquadraService;

    @Autowired
    public GiornataService giornataService;

    @Autowired
    public PartitaService partitaService;

    @Autowired
    public RosaService rosaService;

    @Autowired
    public UtentiService utentiService;

    @Autowired
    public ValutazionePartitaService valutazionePartitaService;

    @GetMapping("/giocatore/all")
    @ResponseBody
    public List<Giocatore> getAllGiocatori() {
        return giocatoreService.findAll();
    }

    @PostMapping("/giocatore/buy")
    @ResponseBody
    public Set<Giocatore> buyGiocatori(
            @RequestBody AcquistoGiocatoriRequest request
    ) throws Exception {
        Objects.requireNonNull(request.getUtente());
        Objects.requireNonNull(request.getGiocatore());

        Giocatore giocatore = request.getGiocatore();

        //per aver i giocatori aggiornati
        Utente utente = utentiService.getUserById(request.getUtente().getId_utente()).get();

        Squadra squadra = squadraService.getSquadraById(utente.getId_squadra()).get();

        Set<Giocatore> giocatoriSquadra = squadra.getGiocatori_acquistati();

        List<Giocatore> giocatoreGiaPresente = giocatoriSquadra.stream().filter((g -> g.getId_giocatore() == giocatore.getId_giocatore())).toList();
        if (!giocatoreGiaPresente.isEmpty()) {
            throw new Exception("Giocatore gia Presente");
        } else if (giocatoriSquadra.size() == 12) {
            throw new Exception("Massimo Numero Giocatori");
        } if (isMaxGiocatoriRuolo(giocatoriSquadra, giocatore.getEruolo())) {
            throw new Exception("Massimo Numero Giocatori Stesso ruolo");
        }

        giocatoriSquadra.add(giocatore);
        squadra.setCrediti_residui(squadra.getCrediti_residui() - giocatore.getCosto());

        squadra.setNumero_giocatori_acquistati(squadra.getNumero_giocatori_acquistati() + 1);
        squadra.setGiocatori_acquistati(giocatoriSquadra);
        squadraService.putSquadra(squadra);

        return giocatoriSquadra;
    }

    private boolean isMaxGiocatoriRuolo(Set<Giocatore> giocatoriSquadra, String eruolo) {
        switch (eruolo) {
            case "POR":
                return countGiocatoriRuolo(giocatoriSquadra, eruolo) == 2;
            case "DIF":
                return countGiocatoriRuolo(giocatoriSquadra, eruolo) == 6;
            case "ATT":
                return countGiocatoriRuolo(giocatoriSquadra, eruolo) == 4;
        }
        return false;
    }

    private int countGiocatoriRuolo(Set<Giocatore> giocatoriSquadra, String ruolo) {
        return (int) giocatoriSquadra.stream().filter(giocatore -> giocatore.getEruolo().equals(ruolo)).count();
    }

    @PutMapping("/giocatore/eliminaTitolare/{id}")
    @ResponseBody
    public void deleteGiocatori(
            @RequestBody EliminaGiocatoreRequest request
    ) {;

        Giocatore giocatore = request.getGiocatore();

        //per aver i giocatori aggiornati
        Utente utente = utentiService.getUserById(request.getId_utente()).get();

        Squadra squadra = squadraService.getSquadraById(utente.getId_squadra()).get();

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

    @PostMapping("/giocatore/sell")
    @ResponseBody
    public Set<Giocatore> sellGiocatori(
            @RequestBody AcquistoGiocatoriRequest request
    ) throws Exception {
        Objects.requireNonNull(request.getUtente());
        Objects.requireNonNull(request.getGiocatore());

        Giocatore giocatore = request.getGiocatore();

        //per aver i giocatori aggiornati
        Utente utente = utentiService.getUserById(request.getUtente().getId_utente()).get();

        Squadra squadra = squadraService.getSquadraById(utente.getId_squadra()).get();

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

    @PutMapping("/giocatore/inserisciGiocatore")
    @ResponseBody
    public void inserisciGiocatore(
            @NotNull @RequestBody InserisciGiocatoreRequest request
    ) throws Exception {
        Objects.requireNonNull(request.getNomeGiocatore());
        Objects.requireNonNull(request.getRuolo());

        String nomeGiocatore = request.getNomeGiocatore();
        long valutazione = request.getValutazione();
        String ruolo = request.getRuolo();

        long nextId = giocatoreService.getMax()+1;
        giocatoreService.putGiocatore(valutazione,nextId,ruolo,nomeGiocatore);
    }

    @PostMapping("/giocatore/inserisciValutazioneGiocatore")
    @ResponseBody
    public void inserisciValutazioneGiocatore(
            @NotNull
            @RequestBody InserisciValutazioneGiocatoreRequest request
    ) {
        Objects.requireNonNull(request.getGiocatore());

        Giocatore nomeGiocatore = request.getGiocatore();
        long valutazione = request.getValutazione();
        giocatoreService.inserisciValutazioneGiocatore(nomeGiocatore,valutazione);
    }

    @GetMapping("/formation/all")
    @ResponseBody
    public List<Rosa> getAllRose() {
        return rosaService.getAllRose();
    }

    @PostMapping("/formation/aggiornaFormazione")
    @ResponseBody
    public FormazioneResponse putFormazione(
            @RequestBody AggiornaFormazioneRequest request
    ) {
        Objects.requireNonNull(request.getUtente());
        Objects.requireNonNull(request.getRosa());

        Rosa rosa = request.getRosa();
        Utente utente = request.getUtente();
        Squadra squadra = squadraService.getSquadraById(utente.getId_utente()).get();

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

    @PostMapping("/formation/getGiocatori")
    @ResponseBody
    public List<Giocatore> getGiocatori(
            @RequestBody GiocatoriRequest request
    ) {
        Objects.requireNonNull(request.getUtente());

        int type = request.getType();
        Utente utente = request.getUtente();
        //per aver i giocatori aggiornati;

        Squadra squadra = squadraService.getSquadraById(utente.getId_utente()).get();

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

    @GetMapping("/formation/getGiocatoriPosseduti/{id}")
    @ResponseBody
    public Set<Giocatore> getGiocatoriPosseduti(
            @PathVariable long id
    ) {
        Utente utente = utentiService.getUserById(id).get();
        long idSquadra = utente.getId_squadra();
        Squadra squadra = squadraService.getSquadraById(idSquadra).get();
        return squadra.getGiocatori_acquistati();
    }

    @GetMapping("/formation/classifica")
    @ResponseBody
    public List<ClassificaResponse> getClassifica() {
        List<ClassificaResponse> classificaResponses = new ArrayList<>();
        long comparatore = -1;
        List<Squadra> squadre = squadraService.findAll();
        long giornataAttuale = partitaService.getGiornataAttuale();
        for (Squadra squadra : squadre) {
            long sommaPunteggio = 0;
            List<TitolariSquadra> titolariSquadra = titolariSquadraService.getTitolariSquadraById(squadra.getId_squadra());

            if (titolariSquadra.isEmpty()) {
                ClassificaResponse classificaResponse = new ClassificaResponse();
                classificaResponse.setPunteggio(0);
                classificaResponse.setNome(squadra.getNome_squadra());
                classificaResponse.setIdSquadra(squadra.getId_squadra());
                classificaResponses.add(classificaResponse);
                continue;
            }

            for (TitolariSquadra giocatore : titolariSquadra) {
                long id_titolare = giocatore.getId_giocatore();
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

    @GetMapping("/matchs/all")
    @ResponseBody
    public List<Partita> getAllMatchs() {
        return partitaService.findAll();
    }

    @PostMapping("/matchs/calcolaGiornata")
    @ResponseBody
    public void calcolaGiornata() {
        giornataService.calcolaGiornata();
    }

    @GetMapping("/matchs/giocatoriPartita/{id}")
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

        Squadra squadra = squadraService.getSquadraById(id).get();
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

    @PostMapping("/titolari/save")
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

    @GetMapping("/titolari/get/{id}")
    @ResponseBody
    public FormazioneResponse saveTitolare(
            @PathVariable long id
    ) {
        return titolariSquadraService.getTitolari(id);
    }

    @PostMapping("/utenti/save")
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

        utente.setId_squadra(squadra.getId_squadra());
        utente.setBactive(false);
        utentiService.addUser(utente);
    }

    @PostMapping("/utenti/login")
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

    @GetMapping("/utenti/user/{id}")
    @ResponseBody
    public UtenteResponse findUser(@PathVariable long id) {

        Utente utente = utentiService.getUserById(id).get();
        Squadra squadra = squadraService.getSquadraById(utente.getId_utente()).get();

        UtenteResponse utenteResponse = new UtenteResponse();
        utenteResponse.setUtente(utente);
        utenteResponse.setSquadra(squadra);
        return utenteResponse;
    }

    @GetMapping("/utenti/all")
    @ResponseBody
    public List<Utente> getAllUsers() {
        return utentiService.getAllUsers();
    }

    @PutMapping("/utenti/autorizza/{id}")
    @ResponseBody
    public void autorizzaUtente(
            @PathVariable long id
    ) {
        utentiService.autorizzaUtente(id);
    }
}