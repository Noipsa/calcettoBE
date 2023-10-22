package com.fanta.calcetto.services;

import com.fanta.calcetto.entities.TitolariEffettiviGiornata;
import com.fanta.calcetto.repository.TitolariEffettiviGiornataRepository;
import com.fanta.calcetto.services.serviceInterface.TitolariEffettiviGiornataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TitolariEffettiviGiornataServiceImpl implements TitolariEffettiviGiornataService {

    @Autowired
    private TitolariEffettiviGiornataRepository titolariEffettiviGiornataRepository;
    @Override
    public List<TitolariEffettiviGiornata> getTitolariEffettiviPrimaDellaGiornataAttuale(long id_squadra, long giornata) {
        return titolariEffettiviGiornataRepository.getTitolariByIdSquadraAndMinGiornata(id_squadra, giornata);
    }
}
