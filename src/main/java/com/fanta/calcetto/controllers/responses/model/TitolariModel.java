package com.fanta.calcetto.controllers.responses.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

public class TitolariModel {
    private List<GiocatoriModel> portieri;
    private List<GiocatoriModel> difensori;
    private List<GiocatoriModel> attaccanti;

    public TitolariModel() {
    }

    public List<GiocatoriModel> getPortieri() {
        return portieri;
    }

    public void setPortieri(List<GiocatoriModel> portieri) {
        this.portieri = portieri;
    }

    public List<GiocatoriModel> getDifensori() {
        return difensori;
    }

    public void setDifensori(List<GiocatoriModel> difensori) {
        this.difensori = difensori;
    }

    public List<GiocatoriModel> getAttaccanti() {
        return attaccanti;
    }

    public void setAttaccanti(List<GiocatoriModel> attaccanti) {
        this.attaccanti = attaccanti;
    }
}

