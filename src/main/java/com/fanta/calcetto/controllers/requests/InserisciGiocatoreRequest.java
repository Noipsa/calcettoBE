package com.fanta.calcetto.controllers.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InserisciGiocatoreRequest {
    String nomeGiocatore;
    long valutazione;
    String ruolo;
}
