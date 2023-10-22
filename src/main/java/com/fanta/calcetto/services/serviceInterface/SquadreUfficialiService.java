package com.fanta.calcetto.services.serviceInterface;

import com.fanta.calcetto.entities.SquadreUfficiali;

import java.util.List;

public interface SquadreUfficialiService {
    public void insertSquadra(String nome);
    public List<SquadreUfficiali> getAllSquadre();
}
