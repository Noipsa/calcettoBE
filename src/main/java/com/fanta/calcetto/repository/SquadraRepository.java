package com.fanta.calcetto.repository;

import com.fanta.calcetto.entities.Squadra;
import com.fanta.calcetto.entities.Utente;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SquadraRepository extends JpaRepository<Squadra, Long> {
    @Query(value = "select * from squadra where id_squadra = ?1", nativeQuery = true)
    Squadra prendiById(long id);
    @Modifying
    @Transactional
    @Query(value = "update squadra set crediti_residui = ?1 where numero_giocatori_acquistati = 0", nativeQuery = true)
    void insertCredito(long credito);
}
