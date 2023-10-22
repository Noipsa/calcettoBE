package com.fanta.calcetto.controllers.requests;

public class InserisciCreditoRequest {
    private long credito;

    @Override
    public String toString() {
        return "InserisciCreditoRequest{" +
                "credito=" + credito +
                '}';
    }

    public InserisciCreditoRequest() {
    }

    public long getCredito() {
        return credito;
    }

    public void setCredito(long credito) {
        this.credito = credito;
    }
}
