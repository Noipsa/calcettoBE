package com.fanta.calcetto.services;

import com.fanta.calcetto.entities.Utente;
import com.fanta.calcetto.repository.UtentiRepository;
import com.fanta.calcetto.services.serviceInterface.UtentiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtentiServiceImpl implements UtentiService {

    @Autowired
    private UtentiRepository utentiRepository;

    @Override
    public void addUser(Utente utente) {
        utentiRepository.save(utente);
    }

    @Override
    public List<Utente> getUsers(Utente utente) {
        return utentiRepository.findByEmailAndPassword(utente.getEmail(), utente.getPassword());
    }

    @Override
    public Optional<Utente> getUserById(long id) {
        return utentiRepository.findById(id);
    }

    @Override
    public List<Utente> getAllUsers() {
        return utentiRepository.findAll();
    }

    @Override
    public void autorizzaUtente(long id) {
        Utente utente = utentiRepository.getById(id);
        utente.setBactive(true);
        utentiRepository.save(utente);
    }

}
