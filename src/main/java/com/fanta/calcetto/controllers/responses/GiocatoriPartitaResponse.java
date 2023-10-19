package com.fanta.calcetto.controllers.responses;

import com.fanta.calcetto.controllers.responses.model.GiocatoriModel;
import com.fanta.calcetto.controllers.responses.model.TitolariModel;
import com.fanta.calcetto.entities.Giocatore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GiocatoriPartitaResponse {
    TitolariModel titolari;
    List<GiocatoriModel> riserve;
}
