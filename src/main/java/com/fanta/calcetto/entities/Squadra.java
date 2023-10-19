package com.fanta.calcetto.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "squadra")
public class Squadra {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_squadra;

    @Column(name = "id_formazione")
    private long id_formazione;

    @Column(name = "nome_squadra")
    private String nome_squadra;

    @Column(name = "crediti_residui", nullable=false)
    private long crediti_residui;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "squadra_giocatori",
            joinColumns = @JoinColumn(name = "id_squadra", referencedColumnName = "id_squadra"),
            inverseJoinColumns = @JoinColumn(name = "id_giocatore", referencedColumnName = "id_giocatore"))
    private Set<Giocatore> giocatori_acquistati;

    @Column(name = "numero_giocatori_acquistati")
    private long numero_giocatori_acquistati;

    @JsonBackReference
    @JsonIgnore
    @OneToMany(mappedBy="prima_squadra", cascade = {CascadeType.ALL})
    private Set<Partita> partite_prima_squadra;

    @JsonBackReference
    @JsonIgnore
    @OneToMany(mappedBy="seconda_squadra", cascade = {CascadeType.ALL})
    private Set<Partita> partite_seconda_squadra;

    @OneToMany(mappedBy="id_squadra", cascade = {CascadeType.ALL})
    private Set<Utente> utente_proprietario_squadra;
}
