package com.fanta.calcetto.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "squadreUfficiali")
public class SquadreUfficiali {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "nome_squadra")
    private String nome_squadra;

    @JsonBackReference
    @JsonIgnore
    @OneToMany(mappedBy="prima_squadra", cascade = {CascadeType.ALL})
    private List<Partita> partite_prima_squadra;

    @JsonBackReference
    @JsonIgnore
    @OneToMany(mappedBy="seconda_squadra", cascade = {CascadeType.ALL})
    private Set<Partita> partite_seconda_squadra;

    @Column(name = "logo")
    private String logo;

    @Override
    public String toString() {
        return "SquadreUfficiali{" +
                "id=" + id +
                ", nome_squadra='" + nome_squadra + '\'' +
                ", partite_prima_squadra=" + partite_prima_squadra +
                ", partite_seconda_squadra=" + partite_seconda_squadra +
                ", logo='" + logo + '\'' +
                '}';
    }

    public SquadreUfficiali() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome_squadra() {
        return nome_squadra;
    }

    public void setNome_squadra(String nome_squadra) {
        this.nome_squadra = nome_squadra;
    }

    public List<Partita> getPartite_prima_squadra() {
        return partite_prima_squadra;
    }

    public void setPartite_prima_squadra(List<Partita> partite_prima_squadra) {
        this.partite_prima_squadra = partite_prima_squadra;
    }

    public Set<Partita> getPartite_seconda_squadra() {
        return partite_seconda_squadra;
    }

    public void setPartite_seconda_squadra(Set<Partita> partite_seconda_squadra) {
        this.partite_seconda_squadra = partite_seconda_squadra;
    }

    public String getLogo() {   
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
