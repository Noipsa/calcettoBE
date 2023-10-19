package com.fanta.calcetto.services;

import com.fanta.calcetto.entities.Rosa;
import com.fanta.calcetto.repository.PartitaRepository;
import com.fanta.calcetto.repository.RosaRepository;
import com.fanta.calcetto.services.serviceInterface.RosaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RosaServiceImpl implements RosaService {
    @Autowired
    private RosaRepository rosaRepository;

    @Override
    public List<Rosa> getAllRose() {
        return rosaRepository.findAll();
    }

    @Override
    public Rosa getRosaById(long id) {
        return rosaRepository.getReferenceById(id);
    }
}
