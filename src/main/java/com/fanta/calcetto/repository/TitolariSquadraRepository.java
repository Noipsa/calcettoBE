package com.fanta.calcetto.repository;

import com.fanta.calcetto.entities.TitolariSquadra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface TitolariSquadraRepository extends JpaRepository<TitolariSquadra, Long> {

    @Query(value = "select * from titolari_squadra where id_squadra = ?1 and giocatori_titolari_id_giocatore = ?2", nativeQuery = true)
    Optional<TitolariSquadra> findByIdAndGiocatori(long id, long idgiocatore);

    @Query(value = "select * from titolari_squadra where id_squadra = ?1", nativeQuery = true)
    List<TitolariSquadra> findAllByIdSquadraTitolare(long id);
}
