package com.fanta.calcetto.services;

import com.fanta.calcetto.entities.SquadreUfficiali;
import com.fanta.calcetto.repository.SquadreUfficialiaRepository;
import com.fanta.calcetto.services.serviceInterface.SquadreUfficialiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SquadreUfficialiServiceImpl implements SquadreUfficialiService {

    @Autowired
    private SquadreUfficialiaRepository squadreUfficialiaRepository;


    @Override
    public void insertSquadra(String nome) {
        if(!nome.isBlank() && !nome.isEmpty()) {
            SquadreUfficiali squadreUfficiali = new SquadreUfficiali();
            squadreUfficiali.setNome_squadra(nome);
            squadreUfficialiaRepository.save(squadreUfficiali);
        }
    }

    @Override
    public List<SquadreUfficiali> getAllSquadre() {
        return squadreUfficialiaRepository.findAll();
    }
}
