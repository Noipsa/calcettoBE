package com.fanta.calcetto.entities;

import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

@Entity
@Table(name = "riserve")
public class Riserve{
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "id_riserva")
    private long id_riserva;

    @Column(name = "id_squadra")
    private long id_squadra;

    @Column(name = "ordine_entrata")
    private long ordine_entrata;

    public Riserve() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_riserva() {
        return id_riserva;
    }

    public void setId_riserva(long id_riserva) {
        this.id_riserva = id_riserva;
    }

    public long getOrdine_entrata() {
        return ordine_entrata;
    }

    public void setOrdine_entrata(long ordine_entrata) {
        this.ordine_entrata = ordine_entrata;
    }

    public long getId_squadra() {
        return id_squadra;
    }

    public void setId_squadra(long id_squadra) {
        this.id_squadra = id_squadra;
    }
}
