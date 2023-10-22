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
import java.util.Optional;

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
        return partitaRepository.getAllOrderByDataPartita();
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

    @Override
    public LocalDateTime getPrimaPartitaGiornata() {
        Partita partita = partitaRepository.getFirstByDataPartitaAndGiornata(getGiornataAttuale());
        return partita.getData_partita();
    }

    @Override
    public void eliminaPartita(long id_partita) {
        partitaRepository.deleteById(id_partita);
    }



    @Override
    public void aggiornaPartita(long id_partita, long risultato_prima_squadra, long risultato_seconda_squadra) {
        partitaRepository.updateRisultatiByIdPartita(id_partita, risultato_prima_squadra, risultato_seconda_squadra);
    }
}
