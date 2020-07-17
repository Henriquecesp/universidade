package com.example.models;

import com.sun.istack.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Universidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String nome;
    @NotNull
    private String cnpj;
    @NotNull
    private String endereco;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "universidade")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Curso> curso = new ArrayList<>();

    public long getId() {
        return id;
    }

    public List<Curso> getCurso() {
        return curso;
    }

    public void setCurso(List<Curso> curso) {
        this.curso = curso;
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

}
