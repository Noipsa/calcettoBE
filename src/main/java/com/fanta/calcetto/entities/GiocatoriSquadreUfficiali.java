package com.fanta.calcetto.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "giocatoriSquadreUfficiali")
public class GiocatoriSquadreUfficiali {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "id_squadra")
    private Long id_squadra;

    @Column(name = "id_giocatore")
    private Long id_giocatore;
}
