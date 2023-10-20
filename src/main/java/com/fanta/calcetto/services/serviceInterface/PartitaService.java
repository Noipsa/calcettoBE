package com.fanta.calcetto.services.serviceInterface;

import com.fanta.calcetto.entities.Partita;

import java.util.List;

public interface PartitaService {

    public List<Partita> findAll();

    public long getGiornataAttuale();
}
