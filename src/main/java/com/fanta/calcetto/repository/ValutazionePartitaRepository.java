package com.fanta.calcetto.repository;

import com.fanta.calcetto.entities.ValutazionePartita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ValutazionePartitaRepository extends JpaRepository<ValutazionePartita, Long> {

    @Query(value = "select * from valutazione_partita where id_giornata <= ?1 and id_giocatore = ?2", nativeQuery = true)
    List<ValutazionePartita> getValutazioneByMinIdGiornataAndIdGiocatore(long idGiornata, long idGiocatore);
    @Query(value = "select * from valutazione_partita where id_giocatore = ?1", nativeQuery = true)
    List<ValutazionePartita> getValutazioneByIdGiocatore(long idGiocatore);
    @Query(value = "select * from valutazione_partita where id_giornata = ?1 and id_giocatore = ?2", nativeQuery = true)
    ValutazionePartita getValutazioneByIdGiornataAndIdGiocatore(long idGiornata, long idGiocatore);
    @Transactional
    @Modifying
    @Query(value = "delete from valutazione_partita where id_giornata = ?1 and id_giocatore = ?2", nativeQuery = true)
    void deleteByIdGiocatoreAndGiornata(long id_giornata, long id_giocatore);
}
