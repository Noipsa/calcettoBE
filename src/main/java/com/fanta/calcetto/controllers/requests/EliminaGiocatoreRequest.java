package com.fanta.calcetto.controllers.requests;

import com.fanta.calcetto.entities.Giocatore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EliminaGiocatoreRequest {
    private Giocatore giocatore;
    private long id_utente;
}
