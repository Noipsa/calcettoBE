package com.fanta.calcetto.controllers.responses;

import com.fanta.calcetto.entities.Giocatore;

import java.util.List;

public class FormazioneResponseRiserve {
    private List<Giocatore> portieri;
    private List<GiocatoriResponse> difensori;
    private List<GiocatoriResponse> attaccanti;

    public FormazioneResponseRiserve() {
    }

    public List<Giocatore> getPortieri() {
        return portieri;
    }

    public void setPortieri(List<Giocatore> portieri) {
        this.portieri = portieri;
    }

    public List<GiocatoriResponse> getDifensori() {
        return difensori;
    }

    public void setDifensori(List<GiocatoriResponse> difensori) {
        this.difensori = difensori;
    }

    public List<GiocatoriResponse> getAttaccanti() {
        return attaccanti;
    }

    public void setAttaccanti(List<GiocatoriResponse> attaccanti) {
        this.attaccanti = attaccanti;
    }
}
