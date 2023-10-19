package com.fanta.calcetto.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "valutazionePartita")
public class ValutazionePartita {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_valutazione;

    @Column(name = "id_partita")
    private long id_partita;

    @ManyToMany(mappedBy = "valutazioni_giocatori")
    @JsonIgnore
    private Set<Giocatore> giocatori_valutazioni;

    @Column(name = "valutazione")
    private long valutazione;
}
