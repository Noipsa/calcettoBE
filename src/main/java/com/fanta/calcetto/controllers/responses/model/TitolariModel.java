package com.fanta.calcetto.controllers.responses.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TitolariModel {
    private List<GiocatoriModel> portieri;
    private List<GiocatoriModel> difensori;
    private List<GiocatoriModel> attaccanti;
}

