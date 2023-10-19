package com.fanta.calcetto.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "giocatore")
public class Giocatore {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_giocatore;

    @Column(name = "nome")
    private String nome;

    @Column(name = "costo")
    private long costo;

    @Column(name = "binfortunato")
    private boolean binfortunato;

    @Column(name = "bsqualificato")
    private boolean bsqualificato;

    @Column(name = "eruolo")
    private String eruolo;

    @ManyToMany(mappedBy = "giocatori_acquistati")
    @JsonIgnore
    private List<Squadra> squadre;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "giocatori_titolari")
    private List<TitolariSquadra> id_titolari_squadra;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "valutazioni_giocatori",
            joinColumns = @JoinColumn(name = "id_giocatore"),
            inverseJoinColumns = @JoinColumn(name = "id_valutazione"))
    private Set<ValutazionePartita> valutazioni_giocatori;
}
