package com.fanta.calcetto.services.serviceInterface;

import com.fanta.calcetto.entities.TitolariEffettiviGiornata;

import java.util.List;

public interface TitolariEffettiviGiornataService {
    List<TitolariEffettiviGiornata> getTitolariEffettiviPrimaDellaGiornataAttuale(long id_squadra, long giornata);
}
