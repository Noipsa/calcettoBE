package com.fanta.calcetto.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "rosa")
public class Rosa {

    @Id
    @GeneratedValue
    private Long id_formazione;

    @Column(name = "numero_difensori")
    private int numero_difensori;

    @Column(name = "numero_attaccanti")
    private int numero_attaccanti;

    public Rosa() {
    }

    public Long getId_formazione() {
        return id_formazione;
    }

    public void setId_formazione(Long id_formazione) {
        this.id_formazione = id_formazione;
    }

    public int getNumero_difensori() {
        return numero_difensori;
    }

    public void setNumero_difensori(int numero_difensori) {
        this.numero_difensori = numero_difensori;
    }

    public int getNumero_attaccanti() {
        return numero_attaccanti;
    }

    public void setNumero_attaccanti(int numero_attaccanti) {
        this.numero_attaccanti = numero_attaccanti;
    }
}
