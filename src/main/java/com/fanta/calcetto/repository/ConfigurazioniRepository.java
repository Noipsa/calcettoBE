package com.fanta.calcetto.repository;

import com.fanta.calcetto.entities.Configurazioni;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigurazioniRepository extends JpaRepository<Configurazioni, Long> {
    Configurazioni findByProprieta(String proprieta);
}
