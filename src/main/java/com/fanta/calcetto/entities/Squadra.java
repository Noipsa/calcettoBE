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
@Table(name = "squadra")
public class Squadra {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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

    @Override
    public String toString() {
        return "Squadra{" +
                "id_squadra=" + id_squadra +
                ", id_formazione=" + id_formazione +
                ", nome_squadra='" + nome_squadra + '\'' +
                ", crediti_residui=" + crediti_residui +
                ", giocatori_acquistati=" + giocatori_acquistati +
                ", numero_giocatori_acquistati=" + numero_giocatori_acquistati +
                '}';
    }

    public Squadra() {
    }

    public long getId_squadra() {
        return id_squadra;
    }

    public void setId_squadra(long id_squadra) {
        this.id_squadra = id_squadra;
    }

    public long getId_formazione() {
        return id_formazione;
    }

    public void setId_formazione(long id_formazione) {
        this.id_formazione = id_formazione;
    }

    public String getNome_squadra() {
        return nome_squadra;
    }

    public void setNome_squadra(String nome_squadra) {
        this.nome_squadra = nome_squadra;
    }

    public long getCrediti_residui() {
        return crediti_residui;
    }

    public void setCrediti_residui(long crediti_residui) {
        this.crediti_residui = crediti_residui;
    }

    public Set<Giocatore> getGiocatori_acquistati() {
        return giocatori_acquistati;
    }

    public void setGiocatori_acquistati(Set<Giocatore> giocatori_acquistati) {
        this.giocatori_acquistati = giocatori_acquistati;
    }

    public long getNumero_giocatori_acquistati() {
        return numero_giocatori_acquistati;
    }

    public void setNumero_giocatori_acquistati(long numero_giocatori_acquistati) {
        this.numero_giocatori_acquistati = numero_giocatori_acquistati;
    }
}
