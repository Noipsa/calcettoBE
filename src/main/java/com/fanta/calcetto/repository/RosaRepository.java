package com.fanta.calcetto.repository;

import com.fanta.calcetto.entities.Rosa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RosaRepository extends JpaRepository<Rosa, Long> {
}
