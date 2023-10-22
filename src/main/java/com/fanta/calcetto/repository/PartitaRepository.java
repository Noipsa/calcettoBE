package com.fanta.calcetto.repository;

import com.fanta.calcetto.entities.Partita;
import jakarta.transaction.Transactional;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartitaRepository extends JpaRepository<Partita, Long> {
    @Query(value = "select * from partita where numero_giornata = ?1 order by data_partita asc limit 1", nativeQuery = true)
    Partita getFirstByDataPartitaAndGiornata(long giornata);

    @Query(value = "select * from partita order by numero_giornata asc", nativeQuery = true)
    List<Partita> getAllOrderByDataPartita();

    @Modifying
    @Transactional
    @Query(value = "update partita set risultato_prima_squadra = ?2, risultato_seconda_squadra =?3 where id_partita = ?1", nativeQuery = true)
    void updateRisultatiByIdPartita(long id_partita, long risultato_prima_partita, long risultato_seconda_partita);
}
