package com.fanta.calcetto.services;

import com.fanta.calcetto.entities.ValutazionePartita;
import com.fanta.calcetto.repository.ValutazionePartitaRepository;
import com.fanta.calcetto.services.serviceInterface.ValutazionePartitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ValutazionePartitaServiceImpl implements ValutazionePartitaService {
    @Autowired
    private ValutazionePartitaRepository valutazionePartitaRepository;


    @Override
    public List<ValutazionePartita> getValutazioneByMinIdGiornataAndIdGiocatore(long idGiornata, long idGiocatore) {
        return valutazionePartitaRepository.getValutazioneByMinIdGiornataAndIdGiocatore(idGiornata, idGiocatore);
    }

    @Override
    public ValutazionePartita getValutazioneByIdGiornataAndIdGiocatore(long idGiornata, long idGiocatore) {
        return valutazionePartitaRepository.getValutazioneByIdGiornataAndIdGiocatore(idGiornata, idGiocatore);
    }
}
