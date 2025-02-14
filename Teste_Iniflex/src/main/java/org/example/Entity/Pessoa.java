package org.example.Entity;

import java.time.LocalDate;

//Superclasse Pessoa, resolvi criá-la como uma classe abstrata pois ela não será instaciada.

//Possui os atributos nome(String) e dataNascimento(LocalDate), assim como seus getters e setters, e um construtor básico para que esses
//atributos possam ser usados pela subclasse.

abstract class Pessoa {

    private String nome;
    private LocalDate dataNascimento;

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public LocalDate getDataNascimento(){
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento){
        this.dataNascimento = dataNascimento;
    }

    public Pessoa(String nome, LocalDate dataNascimento){
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }
}
