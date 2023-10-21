package com.fanta.calcetto.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
@Entity
@Table(name = "utenti")
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_utente;

    @Column(name = "id_squadra")
    private Long id_squadra;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "bactive")
    private boolean bactive;

    @Override
    public String toString() {
        return "Utente{" +
                "id_utente=" + id_utente +
                ", id_squadra=" + id_squadra +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", bactive=" + bactive +
                '}';
    }

    public Utente() { }

    public Long getId_utente() {
        return id_utente;
    }

    public void setId_utente(Long id_utente) {
        this.id_utente = id_utente;
    }

    public Long getId_squadra() {
        return id_squadra;
    }

    public void setId_squadra(Long id_squadra) {
        this.id_squadra = id_squadra;
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

    public boolean isBactive() {
        return bactive;
    }

    public void setBactive(boolean bactive) {
        this.bactive = bactive;
    }
}
