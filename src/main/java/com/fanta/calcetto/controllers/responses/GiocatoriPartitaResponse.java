package com.fanta.calcetto.controllers.responses;

import com.fanta.calcetto.controllers.responses.model.GiocatoriModel;
import com.fanta.calcetto.controllers.responses.model.TitolariModel;
import com.fanta.calcetto.entities.Giocatore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

public class GiocatoriPartitaResponse {
    TitolariModel titolari;
    List<GiocatoriModel> riserve;

    public GiocatoriPartitaResponse() {
    }

    public TitolariModel getTitolari() {
        return titolari;
    }

    public void setTitolari(TitolariModel titolari) {
        this.titolari = titolari;
    }

    public List<GiocatoriModel> getRiserve() {
        return riserve;
    }

    public void setRiserve(List<GiocatoriModel> riserve) {
        this.riserve = riserve;
    }
}
