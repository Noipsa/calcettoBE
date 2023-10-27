package com.fanta.calcetto.controllers.responses;

public class ValutazioneMatchsResponse {
    private String nome;
    private long valutazione;

    public ValutazioneMatchsResponse() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getValutazione() {
        return valutazione;
    }

    public void setValutazione(long valutazione) {
        this.valutazione = valutazione;
    }
}
