package com.example.model;

public class Treino {
    private Integer id;
    private String nomeTreino;

    public Treino(Integer id, String nomeTreino) {
        this.id = id;
        this.nomeTreino = nomeTreino;
    }
    

    public String getNomeTreino() {
        return nomeTreino;
    }

    public void setNomeTreino(String nome) {
        this.nomeTreino = nome;
    }


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public Treino nome(String nome) {
        this.nomeTreino = nome;
        return this;
    }

    @Override
    public String toString() {
        return nomeTreino;
    }
    
}
