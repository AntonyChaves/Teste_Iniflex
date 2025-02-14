package org.example.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;

//Subclasse Funcionario que extende a classe Pessoa, essa classe herda os atributos da classe Pessoa e possui seus próprios atributos também
//como salario(BigDecimal) e funcao(String), possui os getters e setters desses atributos, assim como um construtor que utiliza a função super
//para utilizar as variáveis herdadas.

public class Funcionario extends Pessoa{

    private BigDecimal salario;
    private String funcao;

    public String getFuncao(){
        return funcao;
    }

    public void setFuncao(String funcao){
        this.funcao = funcao;
    }

    public BigDecimal getSalario(){
        return salario;
    }

    public void setSalario(BigDecimal salario){
        this.salario = salario;
    }

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao){
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }



}
