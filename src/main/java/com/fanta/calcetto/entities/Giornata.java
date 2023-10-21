package com.fanta.calcetto.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "giornata")
public class Giornata {
    @Id
    private long id_giornata;

    @Override
    public String toString() {
        return "Giornata{" +
                "id_giornata=" + id_giornata +
                '}';
    }

    public Giornata() { }

    public long getId_giornata() {
        return id_giornata;
    }

    public void setId_giornata(long id_giornata) {
        this.id_giornata = id_giornata;
    }
}
