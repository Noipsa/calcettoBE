package com.fanta.calcetto.services;

import com.fanta.calcetto.entities.Partita;
import com.fanta.calcetto.entities.SquadreUfficiali;
import com.fanta.calcetto.repository.GiornataRepository;
import com.fanta.calcetto.repository.PartitaRepository;
import com.fanta.calcetto.repository.SquadreUfficialiaRepository;
import com.fanta.calcetto.services.serviceInterface.PartitaService;
import com.fanta.calcetto.services.serviceInterface.SquadreUfficialiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PartitaServiceImpl implements PartitaService {
    @Autowired
    private PartitaRepository partitaRepository;
    @Autowired
    private GiornataRepository giornataRepository;
    @Autowired
    private SquadreUfficialiaRepository squadreUfficialiaRepository;
    @Override
    public List<Partita> findAll() {
        return partitaRepository.findAll();
    }

    @Override
    public long getGiornataAttuale() {
        return giornataRepository.getMax();
    }

    @Override
    public void inserisciPartita(long giornata, long id_prima_squadra, long id_seconda_squadra, LocalDateTime data_partita) {
        Partita partita = new Partita();
        partita.setData_partita(data_partita);
        partita.setPrima_squadra(squadreUfficialiaRepository.findById(id_prima_squadra).get());
        partita.setSeconda_squadra(squadreUfficialiaRepository.findById(id_seconda_squadra).get());
        partita.setRisultato_prima_squadra(-1);
        partita.setRisultato_seconda_squadra(-1);
        partita.setNumero_giornata(giornata);
        partitaRepository.save(partita);
    }
}
