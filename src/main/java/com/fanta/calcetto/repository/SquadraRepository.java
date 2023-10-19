package com.fanta.calcetto.repository;

import com.fanta.calcetto.entities.Squadra;
import com.fanta.calcetto.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SquadraRepository extends JpaRepository<Squadra, Long> {
    @Query(value = "select * from squadra where id_squadra = ?1", nativeQuery = true)
    Squadra prendiById(long id);
}
