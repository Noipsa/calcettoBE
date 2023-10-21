package com.fanta.calcetto.controllers.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class SaveUserRequest {
    private String email;
    private String password;
    private String nome_squadra;

    public SaveUserRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome_squadra() {
        return nome_squadra;
    }

    public void setNome_squadra(String nome_squadra) {
        this.nome_squadra = nome_squadra;
    }
}
