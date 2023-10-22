package com.fanta.calcetto.controllers.requests;

import java.time.LocalDateTime;

public class InsertPartitaRequest {
    private long id_prima_squadra;
    private long id_seconda_squadra;
    private long giornata;
    private LocalDateTime data_partita;

    @Override
    public String toString() {
        return "InsertPartitaRequest{" +
                "id_prima_squadra=" + id_prima_squadra +
                ", id_seconda_squadra=" + id_seconda_squadra +
                ", giornata=" + giornata +
                ", data_partita=" + data_partita +
                '}';
    }

    public InsertPartitaRequest() {
    }

    public long getId_prima_squadra() {
        return id_prima_squadra;
    }

    public void setId_prima_squadra(long id_prima_squadra) {
        this.id_prima_squadra = id_prima_squadra;
    }

    public long getId_seconda_squadra() {
        return id_seconda_squadra;
    }

    public void setId_seconda_squadra(long id_seconda_squadra) {
        this.id_seconda_squadra = id_seconda_squadra;
    }

    public long getGiornata() {
        return giornata;
    }

    public void setGiornata(long giornata) {
        this.giornata = giornata;
    }

    public LocalDateTime getData_partita() {
        return data_partita;
    }

    public void setData_partita(LocalDateTime data_partita) {
        this.data_partita = data_partita;
    }
}
