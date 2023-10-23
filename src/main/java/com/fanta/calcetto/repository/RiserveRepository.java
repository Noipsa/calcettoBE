package com.fanta.calcetto.repository;

import com.fanta.calcetto.entities.Riserve;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RiserveRepository extends JpaRepository<Riserve, Long> {

    @Query(value = "select * from riserve where id_squadra = ?1", nativeQuery = true)
    List<Riserve> getAllBySquadraId(long id);
    @Modifying
    @Transactional
    @Query(value = "delete from riserve where id_squadra = ?1", nativeQuery = true)
    void eliminaByIdSquadra(long id);
}
