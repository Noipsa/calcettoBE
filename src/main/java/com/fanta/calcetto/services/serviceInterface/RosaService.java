package com.fanta.calcetto.services.serviceInterface;

import com.fanta.calcetto.entities.Rosa;

import java.util.List;

public interface RosaService {
    public List<Rosa> getAllRose();

    public Rosa getRosaById(long id);
}
