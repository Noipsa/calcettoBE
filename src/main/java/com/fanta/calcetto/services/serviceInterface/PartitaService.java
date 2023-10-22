package com.fanta.calcetto.services.serviceInterface;

import com.fanta.calcetto.entities.Partita;

import java.time.LocalDateTime;
import java.util.List;

public interface PartitaService {

    public List<Partita> findAll();
    public long getGiornataAttuale();
    public void inserisciPartita(long giornata, long id_prima_squadra, long id_seconda_squadra, LocalDateTime data_partita);
    public LocalDateTime getPrimaPartitaGiornata();
}
