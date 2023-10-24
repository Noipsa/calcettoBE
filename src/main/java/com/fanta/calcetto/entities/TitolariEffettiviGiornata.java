package com.fanta.calcetto.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "titolariEffettiviGiornata")
public class TitolariEffettiviGiornata {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "id_squadra")
    private Long id_squadra;

    @Column(name = "id_giocatore")
    private Long id_giocatore;

    @Column(name = "giornata")
    private Long giornata;

    public TitolariEffettiviGiornata() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_squadra() {
        return id_squadra;
    }

    public void setId_squadra(Long id_squadra) {
        this.id_squadra = id_squadra;
    }

    public Long getId_giocatore() {
        return id_giocatore;
    }

    public void setId_giocatore(Long id_giocatore) {
        this.id_giocatore = id_giocatore;
    }

    public Long getGiornata() {
        return giornata;
    }

    public void setGiornata(Long giornata) {
        this.giornata = giornata;
    }
}
