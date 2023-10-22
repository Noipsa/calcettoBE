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
@Table(name = "giocatore")
public class Giocatore {

    @Id
    @GeneratedValue
    private Long id_giocatore;

    @Column(name = "nome")
    private String nome;

    @Column(name = "costo")
    private Long costo;

    @Column(name = "binfortunato")
    private boolean binfortunato;

    @Column(name = "bsqualificato")
    private boolean bsqualificato;

    @Column(name = "eruolo")
    private String eruolo;

    @Column(name = "id_squadra_ufficiale")
    private long id_squadra_ufficiale;

    @ManyToMany(mappedBy = "giocatori_acquistati")
    @JsonIgnore
    private List<Squadra> squadre;


    @Override
    public String toString() {
        return "Giocatore{" +
                "id_giocatore=" + id_giocatore +
                ", nome='" + nome + '\'' +
                ", costo=" + costo +
                ", binfortunato=" + binfortunato +
                ", bsqualificato=" + bsqualificato +
                ", eruolo='" + eruolo + '\'' +
                ", id_squadra_ufficiale=" + id_squadra_ufficiale +
                ", squadre=" + squadre +
                '}';
    }

    public Giocatore() { }

    public Long getId_giocatore() {
        return id_giocatore;
    }

    public void setId_giocatore(Long id_giocatore) {
        this.id_giocatore = id_giocatore;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCosto() {
        return costo;
    }

    public void setCosto(Long costo) {
        this.costo = costo;
    }

    public boolean isBinfortunato() {
        return binfortunato;
    }

    public void setBinfortunato(boolean binfortunato) {
        this.binfortunato = binfortunato;
    }

    public boolean isBsqualificato() {
        return bsqualificato;
    }

    public void setBsqualificato(boolean bsqualificato) {
        this.bsqualificato = bsqualificato;
    }

    public String getEruolo() {
        return eruolo;
    }

    public void setEruolo(String eruolo) {
        this.eruolo = eruolo;
    }

    public List<Squadra> getSquadre() {
        return squadre;
    }

    public void setSquadre(List<Squadra> squadre) {
        this.squadre = squadre;
    }

    public long getId_squadra_ufficiale() {
        return id_squadra_ufficiale;
    }

    public void setId_squadra_ufficiale(long id_squadra_ufficiale) {
        this.id_squadra_ufficiale = id_squadra_ufficiale;
    }
}
