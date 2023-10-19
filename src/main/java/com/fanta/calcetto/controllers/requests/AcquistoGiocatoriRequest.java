package com.fanta.calcetto.controllers.requests;

import com.fanta.calcetto.entities.Giocatore;
import com.fanta.calcetto.entities.Utente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AcquistoGiocatoriRequest {
    private Giocatore giocatore;
    private Utente utente;
}
