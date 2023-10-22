package com.fanta.calcetto.controllers.requests;


public class InserisciSquadraRequest {
    private String nomesquadra;

    @Override
    public String toString() {
        return "InserisciSquadraRequest{" +
                "nomesquadra='" + nomesquadra + '\'' +
                '}';
    }

    public InserisciSquadraRequest() {
    }

    public String getNomesquadra() {
        return nomesquadra;
    }

    public void setNomesquadra(String nomesquadra) {
        this.nomesquadra = nomesquadra;
    }
}
