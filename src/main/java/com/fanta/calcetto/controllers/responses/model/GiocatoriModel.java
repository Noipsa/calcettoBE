package com.fanta.calcetto.controllers.responses.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GiocatoriModel {
    private String nome;
    private boolean infortunato_espulso = false;
    private int valutazione;
}