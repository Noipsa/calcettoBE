package com.fanta.calcetto.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;
@Entity
@Table(name = "titolariSquadra")
public class TitolariSquadra {
    @Id
    @GeneratedValue
    private Long id_squadra_titolare;

    @Column(name = "id_squadra")
    private Long id_squadra;

    @Column(name = "id_giocatore")
    private Long id_giocatore;

    @Override
    public String toString() {
        return "TitolariSquadra{" +
                "id_squadra_titolare=" + id_squadra_titolare +
                ", id_squadra=" + id_squadra +
                '}';
    }

    public TitolariSquadra() { }

    public Long getId_squadra_titolare() {
        return id_squadra_titolare;
    }

    public void setId_squadra_titolare(Long id_squadra_titolare) {
        this.id_squadra_titolare = id_squadra_titolare;
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

    public void setId_giocatore(Long giocatori_titolari) {
        this.id_giocatore = giocatori_titolari;
    }
}
