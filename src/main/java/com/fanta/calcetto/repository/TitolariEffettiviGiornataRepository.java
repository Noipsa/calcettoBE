package com.fanta.calcetto.repository;

import com.fanta.calcetto.entities.TitolariEffettiviGiornata;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TitolariEffettiviGiornataRepository extends JpaRepository<TitolariEffettiviGiornata, Long> {

    @Modifying
    @Transactional
    @Query(value = "delete from titolari_effettivi_giornata where id_squadra = ?1 and giornata = ?2", nativeQuery = true)
    void deleteAllByIdSquadraAndGiornata(long id, long giornata);
    @Query(value = "select * from titolari_effettivi_giornata where id_squadra = ?1 and giornata < ?2", nativeQuery = true)
    List<TitolariEffettiviGiornata> getTitolariByIdSquadraAndMinGiornata(long id_squadra, long giornata);

    @Query(value = "select * from titolari_effettivi_giornata where id_squadra = ?1 and giornata = ?2", nativeQuery = true)
    List<TitolariEffettiviGiornata> getTitolariByIdSquadraAndGiornata(long id_squadra, long giornata);
}
