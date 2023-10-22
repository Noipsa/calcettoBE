package com.fanta.calcetto.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Entity
@Table(name = "partita")
public class Partita {

    @Id
    @GeneratedValue
    private long id_partita;

    @Column(name = "numero_giornata", nullable=false)
    private long numero_giornata;

    @JsonManagedReference
    @JoinColumn(name = "prima_squadra", nullable=false)
    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private SquadreUfficiali prima_squadra;

    @JsonManagedReference
    @JoinColumn (name = "seconda_squadra", nullable=false)
    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private SquadreUfficiali seconda_squadra;

    @Column(name = "risultato_prima_squadra")
    private long risultato_prima_squadra;

    @Column(name = "risultato_seconda_squadra")
    private long risultato_seconda_squadra;

    @Column(name = "data_partita", nullable=true)
    private Date data_partita;

    @Override
    public String toString() {
        return "Partita{" +
                "id_partita=" + id_partita +
                ", numero_giornata=" + numero_giornata +
                ", prima_squadra=" + prima_squadra +
                ", seconda_squadra=" + seconda_squadra +
                ", risultato_prima_squadra=" + risultato_prima_squadra +
                ", risultato_seconda_squadra=" + risultato_seconda_squadra +
                ", data_partita=" + data_partita +
                '}';
    }

    public Partita() {
    }

    public long getId_partita() {
        return id_partita;
    }

    public void setId_partita(long id_partita) {
        this.id_partita = id_partita;
    }

    public long getNumero_giornata() {
        return numero_giornata;
    }

    public void setNumero_giornata(long numero_giornata) {
        this.numero_giornata = numero_giornata;
    }

    public SquadreUfficiali getPrima_squadra() {
        return prima_squadra;
    }

    public void setPrima_squadra(SquadreUfficiali prima_squadra) {
        this.prima_squadra = prima_squadra;
    }

    public SquadreUfficiali getSeconda_squadra() {
        return seconda_squadra;
    }

    public void setSeconda_squadra(SquadreUfficiali seconda_squadra) {
        this.seconda_squadra = seconda_squadra;
    }

    public long getRisultato_prima_squadra() {
        return risultato_prima_squadra;
    }

    public void setRisultato_prima_squadra(long risultato_prima_squadra) {
        this.risultato_prima_squadra = risultato_prima_squadra;
    }

    public long getRisultato_seconda_squadra() {
        return risultato_seconda_squadra;
    }

    public void setRisultato_seconda_squadra(long risultato_seconda_squadra) {
        this.risultato_seconda_squadra = risultato_seconda_squadra;
    }

    public Date getData_partita() {
        return data_partita;
    }

    public void setData_partita(Date data_partita) {
        this.data_partita = data_partita;
    }
}
