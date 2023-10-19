package com.fanta.calcetto.repository;

import com.fanta.calcetto.entities.Giocatore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiocatoreRepository extends JpaRepository<Giocatore, Long> {
}
