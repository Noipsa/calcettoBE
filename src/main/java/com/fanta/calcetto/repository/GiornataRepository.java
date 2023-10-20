package com.fanta.calcetto.repository;

import com.fanta.calcetto.entities.Giornata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GiornataRepository extends JpaRepository<Giornata, Long> {

    @Query(value = "select max(id_giornata) from giornata", nativeQuery = true)
    long getMax();
}
