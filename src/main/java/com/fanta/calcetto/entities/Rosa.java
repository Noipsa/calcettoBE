package com.fanta.calcetto.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "rosa")
public class Rosa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_formazione;

    @Column(name = "numero_difensori")
    private int numero_difensori;

    @Column(name = "numero_attaccanti")
    private int numero_attaccanti;
}
