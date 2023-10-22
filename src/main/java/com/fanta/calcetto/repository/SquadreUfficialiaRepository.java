package com.fanta.calcetto.repository;

import com.fanta.calcetto.entities.SquadreUfficiali;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SquadreUfficialiaRepository extends JpaRepository<SquadreUfficiali, Long> {
}
