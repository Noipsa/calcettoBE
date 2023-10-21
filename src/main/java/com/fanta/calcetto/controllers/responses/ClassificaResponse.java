package com.fanta.calcetto.controllers.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ClassificaResponse {
    long punteggio;
    String nome;
    long idSquadra;

    public ClassificaResponse() {
    }

    public long getPunteggio() {
        return punteggio;
    }

    public void setPunteggio(long punteggio) {
        this.punteggio = punteggio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getIdSquadra() {
        return idSquadra;
    }

    public void setIdSquadra(long idSquadra) {
        this.idSquadra = idSquadra;
    }
}
