package com.fanta.calcetto.controllers.responses.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class GiocatoriModel {
    private String nome;
    private boolean infortunato_espulso = false;
    private int valutazione;

    public GiocatoriModel() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isInfortunato_espulso() {
        return infortunato_espulso;
    }

    public void setInfortunato_espulso(boolean infortunato_espulso) {
        this.infortunato_espulso = infortunato_espulso;
    }

    public int getValutazione() {
        return valutazione;
    }

    public void setValutazione(int valutazione) {
        this.valutazione = valutazione;
    }
}