package com.fanta.calcetto.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "titolariSquadra")
public class TitolariSquadra {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_squadra_titolare;

    @Column(name = "id_squadra")
    private long id_squadra;

    @ManyToOne(fetch = FetchType.LAZY)
    private Giocatore giocatori_titolari;
}
