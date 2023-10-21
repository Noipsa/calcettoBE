package com.fanta.calcetto.services.serviceInterface;

import com.fanta.calcetto.entities.Utente;

import java.util.List;
import java.util.Optional;

public interface UtentiService {

    public void addUser(Utente utente);
    public List<Utente> getUsers(Utente utente);
    public Optional<Utente> getUserById(long id);
    public List<Utente> getAllUsers();
    public void autorizzaUtente(long id);
    public void eliminaUtente(long id);
}
