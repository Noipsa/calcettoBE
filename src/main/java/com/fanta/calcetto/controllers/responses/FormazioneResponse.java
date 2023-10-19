package com.fanta.calcetto.controllers.responses;

import com.fanta.calcetto.entities.Giocatore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FormazioneResponse {
    private List<Giocatore> portieri;
    private List<Giocatore> difensori;
    private List<Giocatore> attaccanti;
}
