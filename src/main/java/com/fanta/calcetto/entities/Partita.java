package com.fanta.calcetto.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "partita")
public class Partita {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_partita;

    @Column(name = "numero_giornata", nullable=false)
    private long numero_giornata;

    @JsonManagedReference
    @JoinColumn(name = "prima_squadra", nullable=false)
    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private Squadra prima_squadra;

    @JsonManagedReference
    @JoinColumn (name = "seconda_squadra", nullable=false)
    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private Squadra seconda_squadra;

    @Column(name = "risultato_prima_squadra")
    private long risultato_prima_squadra;

    @Column(name = "risultato_seconda_squadra")
    private long risultato_seconda_squadra;

    @Column(name = "data_partita", nullable=true)
    private Date data_partita;
}
