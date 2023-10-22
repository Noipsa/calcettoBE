package com.fanta.calcetto.controllers.requests;

public class AggiornaPartitaRequest {
    private long id_partita;
    private long risultato_prima_squadra;
    private long risultato_seconda_squadra;

    @Override
    public String toString() {
        return "AggiornaPartitaRequest{" +
                "id_partita=" + id_partita +
                ", risultato_prima_partita=" + risultato_prima_squadra +
                ", risultato_seconda_partita=" + risultato_seconda_squadra +
                '}';
    }

    public AggiornaPartitaRequest() {
    }

    public long getId_partita() {
        return id_partita;
    }

    public void setId_partita(long id_partita) {
        this.id_partita = id_partita;
    }

    public long getRisultato_prima_squadra() {
        return risultato_prima_squadra;
    }

    public void setRisultato_prima_squadra(long risultato_prima_partita) {
        this.risultato_prima_squadra = risultato_prima_partita;
    }

    public long getRisultato_seconda_squadra() {
        return risultato_seconda_squadra;
    }

    public void setRisultato_seconda_squadra(long risultato_seconda_partita) {
        this.risultato_seconda_squadra = risultato_seconda_partita;
    }
}
