package com.example.model;

public class Exercicio {
    
    private Integer id;
    private String nomeExercicio;
    private String repeticoes;
    private String peso;
    private String equipamento;
    private Treino treino;

    public Exercicio(Integer id, String nomeExercicio, String repeticoes, String peso, String equipamento,
            Treino treino) {
        this.id = id;
        this.nomeExercicio = nomeExercicio;
        this.repeticoes = repeticoes;
        this.peso = peso;
        this.equipamento = equipamento;
        this.treino = treino;
    }
    

    public Exercicio(Integer id, String nomeExercicio, String repeticoes, String peso, String equipamento) {
        this.id = id;
        this.nomeExercicio = nomeExercicio;
        this.repeticoes = repeticoes;
        this.peso = peso;
        this.equipamento = equipamento;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeExercicio() {
        return nomeExercicio;
    }

    public void setNomeExercicio(String nomeExercicio) {
        this.nomeExercicio = nomeExercicio;
    }

    public String getRepeticoes() {
        return repeticoes;
    }

    public void setRepeticoes(String repeticoes) {
        this.repeticoes = repeticoes;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(String equipamento) {
        this.equipamento = equipamento;
    }

    public Treino getTreino() {
        return treino;
    }

    public void setTreino(Treino treino) {
        this.treino = treino;
    }


    public Exercicio nome(String nome) {
        this.nomeExercicio = nome;
        return this;
    }

    public Exercicio repeticoes(String repeticoes) {
        this.repeticoes = repeticoes;
        return this;
    }

    public Exercicio peso(String peso) {
        this.peso = peso;
        return this;
    }

    public Exercicio equipamento(String equipamento) {
        this.equipamento = equipamento;
        return this;
    }

    

    

}
