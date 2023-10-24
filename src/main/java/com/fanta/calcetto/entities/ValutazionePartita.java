package com.fanta.calcetto.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
@Entity
@Table(name = "valutazionePartita")
public class ValutazionePartita {

    @Id
    @GeneratedValue
    private long id_valutazione;

    @Column(name = "id_partita")
    private long id_partita;

    @Column(name = "id_giocatore")
    private long id_giocatore;

    @Column(name = "valutazione")
    private long valutazione;

    @Column(name = "id_giornata")
    private long id_giornata;

    public ValutazionePartita() { }

    public long getId_valutazione() {
        return id_valutazione;
    }

    public void setId_valutazione(long id_valutazione) {
        this.id_valutazione = id_valutazione;
    }

    public long getId_partita() {
        return id_partita;
    }

    public void setId_partita(long id_partita) {
        this.id_partita = id_partita;
    }

    public long getId_giocatore() {
        return id_giocatore;
    }

    public void setId_giocatore(long id_giocatore) {
        this.id_giocatore = id_giocatore;
    }

    public long getValutazione() {
        return valutazione;
    }

    public void setValutazione(long valutazione) {
        this.valutazione = valutazione;
    }

    public long getId_giornata() {
        return id_giornata;
    }

    public void setId_giornata(long id_giornata) {
        this.id_giornata = id_giornata;
    }
}
