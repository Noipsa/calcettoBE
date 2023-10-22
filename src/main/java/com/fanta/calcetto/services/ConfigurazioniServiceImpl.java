package com.fanta.calcetto.services;

import com.fanta.calcetto.entities.Configurazioni;
import com.fanta.calcetto.repository.ConfigurazioniRepository;
import com.fanta.calcetto.services.serviceInterface.ConfigurazionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfigurazioniServiceImpl implements ConfigurazionService {

    @Autowired
    private ConfigurazioniRepository configurazioniRepository;
    @Override
    public List<Configurazioni> getConfigurazioni() {
        return configurazioniRepository.findAll();
    }

    @Override
    public void apriMercato() {
        Configurazioni configurazioni = configurazioniRepository.findByProprieta("mercato");
        boolean value =  Boolean.parseBoolean(configurazioni.getValue());
        configurazioni.setValue(String.valueOf(!value));
        configurazioniRepository.save(configurazioni);
    }
    @Override
    public Configurazioni getConfigurazioneByProprieta(String proprieta) {
        return configurazioniRepository.findByProprieta(proprieta);
    }
}
