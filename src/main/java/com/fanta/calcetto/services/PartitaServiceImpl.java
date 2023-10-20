package com.fanta.calcetto.services;

import com.fanta.calcetto.entities.Partita;
import com.fanta.calcetto.repository.GiornataRepository;
import com.fanta.calcetto.repository.PartitaRepository;
import com.fanta.calcetto.services.serviceInterface.PartitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartitaServiceImpl implements PartitaService {
    @Autowired
    private PartitaRepository partitaRepository;

    @Autowired
    private GiornataRepository giornataRepository;
    @Override
    public List<Partita> findAll() {
        return partitaRepository.findAll();
    }

    @Override
    public long getGiornataAttuale() {
        return giornataRepository.getMax();
    }
}
