package com.fanta.calcetto.services.serviceInterface;

import com.fanta.calcetto.entities.Configurazioni;

import java.util.List;

public interface ConfigurazionService {

    public List<Configurazioni> getConfigurazioni();
    public void apriMercato();
    public Configurazioni getConfigurazioneByProprieta(String proprieta);
}
