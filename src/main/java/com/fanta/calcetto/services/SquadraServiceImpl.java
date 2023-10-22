package com.fanta.calcetto.services;

import com.fanta.calcetto.entities.Squadra;
import com.fanta.calcetto.entities.Utente;
import com.fanta.calcetto.repository.SquadraRepository;
import com.fanta.calcetto.repository.UtentiRepository;
import com.fanta.calcetto.services.serviceInterface.SquadraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SquadraServiceImpl implements SquadraService {

    @Autowired
    public SquadraRepository squadraRepository;

    @Autowired
    public UtentiRepository utentiRepository;

    public Squadra addSquadra(String nome_squadra) {
        Squadra squadra = new Squadra();
        squadra.setNome_squadra(nome_squadra);
        return squadraRepository.save(squadra);
    }

    @Override
    public Optional<Squadra> getSquadraById(long id) {
        return squadraRepository.findById(id);
    }

    @Override
    public Squadra putSquadra(Squadra squadra) {
        return squadraRepository.save(squadra);
    }

    @Override
    public List<Squadra> findAll() {
        return squadraRepository.findAll();
    }

    @Override
    public void insertCredito(long credito) {
        squadraRepository.insertCredito(credito);
    }
}
