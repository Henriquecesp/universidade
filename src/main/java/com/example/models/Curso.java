package com.example.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "universidade_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Universidade universidade;

    @OneToOne(fetch = FetchType.LAZY,
    cascade = CascadeType.ALL,
    mappedBy = "curso")
    private Conta conta;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Universidade getUniversidade() {
        return universidade;
    }

    public void setUniversidade(Universidade universidade) {
        this.universidade = universidade;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }
}
