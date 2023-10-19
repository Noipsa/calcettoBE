package com.fanta.calcetto.controllers.requests;

import com.fanta.calcetto.entities.Rosa;
import com.fanta.calcetto.entities.Utente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GiocatoriRequest {
    private int type;
    private Utente utente;
}
