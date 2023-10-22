package com.fanta.calcetto.repository;

import com.fanta.calcetto.entities.Partita;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PartitaRepository extends JpaRepository<Partita, Long> {
    @Query(value = "select * from partita where numero_giornata = ?1 order by data_partita asc limit 1", nativeQuery = true)
    Partita getFirstByDataPartitaAndGiornata(long giornata);
}
