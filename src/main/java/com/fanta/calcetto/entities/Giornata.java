package com.fanta.calcetto.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "giornata")
public class Giornata {
    @Id
    private long id_giornata;
}
