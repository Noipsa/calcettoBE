package com.fanta.calcetto.services.serviceInterface;

import com.fanta.calcetto.entities.Squadra;

import java.util.List;
import java.util.Optional;

public interface SquadraService {
    public Squadra addSquadra(String nome_squadra);

    public Squadra getSquadraByUserId(long id);
    public Squadra putSquadra(Squadra squadra);

    public List<Squadra> findAll();
}
