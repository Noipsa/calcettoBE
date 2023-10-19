package com.fanta.calcetto.repository;

import com.fanta.calcetto.entities.Partita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartitaRepository extends JpaRepository<Partita, Long> {
}
