package com.fanta.calcetto.services;

import com.fanta.calcetto.entities.*;
import com.fanta.calcetto.repository.GiornataRepository;
import com.fanta.calcetto.repository.RiserveRepository;
import com.fanta.calcetto.services.serviceInterface.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class GiornataServiceImpl implements GiornataService {

    @Autowired
    private GiornataRepository giornataRepository;

    @Autowired
    private SquadraService squadraService;

    @Autowired
    private TitolariEffettiviGiornataService titolariEffettiviGiornataService;

    @Autowired
    private ValutazionePartitaService valutazionePartitaService;

    @Autowired
    private RiserveRepository riserveRepository;

    @Autowired
    private GiocatoreService giocatoreService;
    @Override
    public void calcolaGiornata() {

        List<Squadra> squadre = squadraService.findAll();
        List<Giocatore> giocatori = giocatoreService.findAll();

        for (Squadra squadra : squadre) {
            List<TitolariEffettiviGiornata> titolariSquadra = titolariEffettiviGiornataService.getTitolariByIdSquadraAndGiornata(squadra.getId_squadra(), giornataRepository.getMax());

            int difCounter = 1;
            int attCounter = 1;
            List<TitolariEffettiviGiornata> titolariSquadraSostituzioni = new ArrayList<>();
            for (TitolariEffettiviGiornata titolare: titolariSquadra) {
                ValutazionePartita valutazionePartita =
                        valutazionePartitaService
                                .getValutazioneByIdGiornataAndIdGiocatore(giornataRepository.getMax(), titolare.getId_giocatore());


                if (valutazionePartita == null) {
                    List<Riserve> riserve = riserveRepository.getAllBySquadraId(squadra.getId_squadra());

                    Giocatore giocatore = giocatori.stream().filter(g -> Objects.equals(g.getId_giocatore(), titolare.getId_giocatore()) ).toList().get(0);


                    if (giocatore.getEruolo().equals("ATT")) {
                        boolean sostituito = false;
                        sostituisciAttaccante(sostituito, riserve, giocatori, attCounter, titolare, titolariSquadraSostituzioni);
                        attCounter += 1;
                    } else if (giocatore.getEruolo().equals("DIF")) {
                        boolean sostituito = false;
                        sostituisciDifensore(sostituito, riserve, giocatori, difCounter, titolare, titolariSquadraSostituzioni);
                        difCounter += 1;
                    } else if (giocatore.getEruolo().equals("POR")) {
                        boolean sostituito = false;
                        for (Riserve riserva : riserve) {
                            List<Giocatore> riservaRuolo = giocatori.stream().filter(g ->  g.getEruolo().equals("POR") && Objects.equals(g.getId_giocatore(), riserva.getId_riserva())).toList();
                            Giocatore riservaTrovata = null;
                            if (!riservaRuolo.isEmpty()) { riservaTrovata = riservaRuolo.get(0); }
                            ValutazionePartita valutazionePartitaSostituto =
                                    valutazionePartitaService
                                            .getValutazioneByIdGiornataAndIdGiocatore(giornataRepository.getMax(), riserva.getId_riserva());
                            if (riservaTrovata != null && valutazionePartitaSostituto != null && !sostituito) {
                                titolare.setId_giocatore(riservaTrovata.getId_giocatore());
                                titolariSquadraSostituzioni.add(titolare);
                                sostituito = true;
                            }
                        }
                    }
                }
            }

            titolariEffettiviGiornataService.saveSostuzioni(titolariSquadraSostituzioni);
        }

        if (giornataRepository.exists() != 0) {
            Giornata giornata = new Giornata();
            giornata.setId_giornata(giornataRepository.getMax() + 1);
            giornataRepository.save(giornata);
        } else {
            giornataRepository.save(new Giornata());
        }
    }

    private void sostituisciDifensore(boolean sostituito, List<Riserve> riserve, List<Giocatore> giocatori, int difCounter, TitolariEffettiviGiornata titolare, List<TitolariEffettiviGiornata> titolariSquadraSostituzioni) {
        for (Riserve riserva : riserve) {
            long ordine = riserva.getOrdine_entrata();
            List<Giocatore> riservaRuolo = giocatori.stream().filter(g ->  g.getEruolo().equals("DIF") && Objects.equals(g.getId_giocatore(), riserva.getId_riserva())).toList();
            Giocatore riservaTrovata = null;
            if (!riservaRuolo.isEmpty()) { riservaTrovata = riservaRuolo.get(0); }
            ValutazionePartita valutazionePartitaSostituto =
                    valutazionePartitaService
                            .getValutazioneByIdGiornataAndIdGiocatore(giornataRepository.getMax(), riserva.getId_riserva());
            if (riservaTrovata != null && difCounter == ordine && valutazionePartitaSostituto != null
                    && !sostituito && titolariSquadraSostituzioni.stream().noneMatch(r -> r.getId_giocatore().equals(riserva.getId_riserva()))) {
                titolare.setId_giocatore(riservaTrovata.getId_giocatore());
                titolariSquadraSostituzioni.add(titolare);
                sostituito = true;
            } else if (riservaTrovata != null && difCounter == ordine && !sostituito && valutazionePartitaSostituto == null) {
                difCounter += 1;
                sostituisciDifensore(sostituito, riserve, giocatori, difCounter, titolare, titolariSquadraSostituzioni);
            }
        }
    }

    private void sostituisciAttaccante(boolean sostituito, List<Riserve> riserve, List<Giocatore> giocatori, int attCounter, TitolariEffettiviGiornata titolare, List<TitolariEffettiviGiornata> titolariSquadraSostituzioni) {
        for (Riserve riserva : riserve) {
            long ordine = riserva.getOrdine_entrata();
            List<Giocatore> riservaRuolo = giocatori.stream().filter(g ->  g.getEruolo().equals("ATT") && Objects.equals(g.getId_giocatore(), riserva.getId_riserva())).toList();
            Giocatore riservaTrovata = null;
            if (!riservaRuolo.isEmpty()) { riservaTrovata = riservaRuolo.get(0); }
            ValutazionePartita valutazionePartitaSostituto =
                    valutazionePartitaService
                            .getValutazioneByIdGiornataAndIdGiocatore(giornataRepository.getMax(), riserva.getId_riserva());
            if (riservaTrovata != null && attCounter == ordine && valutazionePartitaSostituto != null
                    && !sostituito  && titolariSquadraSostituzioni.stream().noneMatch(r -> r.getId_giocatore().equals(riserva.getId_riserva()))) {
                titolare.setId_giocatore(riservaTrovata.getId_giocatore());
                titolariSquadraSostituzioni.add(titolare);
                sostituito = true;
            } else if (riservaTrovata != null && attCounter == ordine && !sostituito && valutazionePartitaSostituto == null) {
                attCounter += 1;
                sostituisciAttaccante(sostituito, riserve, giocatori, attCounter, titolare, titolariSquadraSostituzioni);
            }
        }
    }

}
