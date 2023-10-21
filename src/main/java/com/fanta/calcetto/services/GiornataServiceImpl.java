package com.fanta.calcetto.services;

import com.fanta.calcetto.entities.Giornata;
import com.fanta.calcetto.repository.GiornataRepository;
import com.fanta.calcetto.services.serviceInterface.GiornataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GiornataServiceImpl implements GiornataService {

    @Autowired
    private GiornataRepository giornataRepository;
    @Override
    public void calcolaGiornata() {
        if (giornataRepository.exists() != 0) {
            Giornata giornata = new Giornata();
            giornata.setId_giornata(giornataRepository.getMax() + 1);
            giornataRepository.save(giornata);
        } else {
            giornataRepository.save(new Giornata());
        }
    }
}
