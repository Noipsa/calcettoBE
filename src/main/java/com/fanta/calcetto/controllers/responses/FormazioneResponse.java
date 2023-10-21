package com.fanta.calcetto.controllers.responses;

import com.fanta.calcetto.entities.Giocatore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

public class FormazioneResponse {
    private List<Giocatore> portieri;
    private List<Giocatore> difensori;
    private List<Giocatore> attaccanti;

    public FormazioneResponse() {
    }

    public List<Giocatore> getPortieri() {
        return portieri;
    }

    public void setPortieri(List<Giocatore> portieri) {
        this.portieri = portieri;
    }

    public List<Giocatore> getDifensori() {
        return difensori;
    }

    public void setDifensori(List<Giocatore> difensori) {
        this.difensori = difensori;
    }

    public List<Giocatore> getAttaccanti() {
        return attaccanti;
    }

    public void setAttaccanti(List<Giocatore> attaccanti) {
        this.attaccanti = attaccanti;
    }
}
