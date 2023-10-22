package com.fanta.calcetto.controllers.requests;

import com.fanta.calcetto.entities.Rosa;
import com.fanta.calcetto.entities.Utente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class AggiornaFormazioneRequest {
    private Rosa rosa;
    private Long id_utente;

    @Override
    public String toString() {
        return "AggiornaFormazioneRequest{" +
                "rosa=" + rosa +
                ", id_utente=" + id_utente +
                '}';
    }

    public AggiornaFormazioneRequest() {
    }

    public Rosa getRosa() {
        return rosa;
    }

    public void setRosa(Rosa rosa) {
        this.rosa = rosa;
    }

    public Long getId_utente() {
        return id_utente;
    }

    public void setId_utente(Long id_utente) {
        this.id_utente = id_utente;
    }
}
