package com.fanta.calcetto.repository;

import com.fanta.calcetto.entities.Giocatore;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiocatoreRepository extends JpaRepository<Giocatore, Long> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO giocatore (binfortunato, bsqualificato, costo, id_giocatore, eruolo, nome)" +
            "VALUES (0,0,?1,?2,?3,?4 )", nativeQuery = true)
    void putGiocatore(long costo,long maxId, String ruolo, String nomeGiocatore);

    @Query(value = "select max(id_giocatore) from giocatore", nativeQuery = true)
    long getMax();

}
