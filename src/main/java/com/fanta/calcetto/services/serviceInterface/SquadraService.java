package com.fanta.calcetto.services.serviceInterface;

import com.fanta.calcetto.entities.Riserve;
import com.fanta.calcetto.entities.Squadra;

import java.util.List;
import java.util.Optional;

public interface SquadraService {
    public Squadra addSquadra(String nome_squadra);
    public Optional<Squadra> getSquadraById(long id);
    public Squadra putSquadra(Squadra squadra);

    public List<Squadra> findAll();
    public void insertCredito(long credito);

    public List<Riserve> getRiserveByIdSquadra(long id);

    public void eliminaRiserve(long id);
}
