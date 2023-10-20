package com.fanta.calcetto.repository;

import com.fanta.calcetto.entities.ValutazionePartita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ValutazionePartitaRepository extends JpaRepository<ValutazionePartita, Long> {

    @Query(value = "select * from valutazione_partita where id_giornata < ?1 and id_giocatore = ?2", nativeQuery = true)
    List<ValutazionePartita> getValutazioneByMinIdGiornataAndIdGiocatore(long idGiornata, long idGiocatore);
}
