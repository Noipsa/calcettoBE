package com.fanta.calcetto.services;

import com.fanta.calcetto.entities.Giocatore;
import com.fanta.calcetto.repository.GiocatoreRepository;
import com.fanta.calcetto.repository.PartitaRepository;
import com.fanta.calcetto.services.serviceInterface.GiocatoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiocatoreServiceImpl implements GiocatoreService {

    @Autowired
    private GiocatoreRepository giocatoreRepository;

    @Override
    public List<Giocatore> findAll() {
        return giocatoreRepository.findAll();
    }

    @Override
    public void putGiocatore(long costo,long maxId,String ruolo, String nomeGiocatore) {
        giocatoreRepository.putGiocatore(costo, maxId,ruolo, nomeGiocatore);
    }

    @Override
    public long getMax() {
        return giocatoreRepository.getMax();
    }


}
