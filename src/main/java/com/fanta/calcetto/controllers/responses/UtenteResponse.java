package com.fanta.calcetto.controllers.responses;

import com.fanta.calcetto.entities.Squadra;
import com.fanta.calcetto.entities.Utente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UtenteResponse {
    private Utente utente;
    private Squadra squadra;
}
