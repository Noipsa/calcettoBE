package com.fanta.calcetto.repository;

import com.fanta.calcetto.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtentiRepository extends JpaRepository<Utente, Long> {

    List<Utente> findByEmailAndPassword(String email, String password);
}
