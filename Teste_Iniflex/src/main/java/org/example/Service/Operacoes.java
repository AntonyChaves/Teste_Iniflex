package org.example.Service;

import org.example.Entity.Funcionario;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

//Classe Operacoes, responsável por conter os métodos que irão executar as principais ações do projeto

public class Operacoes {

    public Operacoes(){

    }
            //Método para inserir novos funcionários, recebe a lista dos funcionários atuais e os dados do novo usuário
    public List<Funcionario> inserirFuncionario (List<Funcionario> funcionarios, String nome,
                                                 String dataNascimento,Double sal, String funcao){

        BigDecimal salario = new BigDecimal(sal);   //Recebe salario como Double mas converte para BigDecimal

        //Formatador de data para os dados passados pelo usuário em formato brasileiro serem salvos de uma forma que o LocalDate entenda.
        DateTimeFormatter formatadorDeData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate nascimento = LocalDate.parse(dataNascimento, formatadorDeData);

        Funcionario novoFuncionario = new Funcionario(nome, nascimento, salario, funcao);       //Criação do novo funcionário utilizando os
        funcionarios.add(novoFuncionario);                                                      //dados passados
        System.out.println("Funcionário cadastrado com sucesso:");
        imprimirFuncionario(novoFuncionario);                           //Aqui utilizo o método de impressão de funcionário para mostrar o
        System.out.println("\n");                                       //novo funcionário cadastrado
        return funcionarios;
    }






    //Método para remover funcionários, ele recebe a lista de funcionários e o nome do funcionário que será removido
    //Faz um foreach para os elementos da lista, identifica o elemento com o nome passado e o exclui, imprimi uma mensagem avisando
    //Caso não seja encontrado, uma mensagem é exibida dizendo que o funcionário informado não foi encontrado
    public List<Funcionario> removerFuncionario( List<Funcionario> funcionarios, String nome){

        Boolean funcionarioExiste = false;

        for(Funcionario funcionario : funcionarios){
                if (funcionario.getNome().equals(nome)) {
                    funcionarios.remove(funcionario);
                    System.out.println("O(a) funcionário(a) " + nome + " foi excluído(a) com sucesso! \n");
                    funcionarioExiste = true;
                    break;
                }
        }
        if (funcionarioExiste == false){
            System.out.println("O(a) funcionário(a) informado não foi encontrado! \n");
        }
        return funcionarios;
    }






        //Método responsável por imprimir os dados de um funcionário passado
    public void imprimirFuncionario(Funcionario funcionario){

        //Formatador de data para ser mostrada no estilo brasileiro
        DateTimeFormatter formatadorDeData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DecimalFormat formatadorDeNum = new DecimalFormat("###,###.##");     //Formatador para colocar ponto nas casas de milhares e
                                                                                    // vírgula nas casas decimais do salário

            System.out.println(

                            funcionario.getNome() + "\t" +
                            formatadorDeData.format(funcionario.getDataNascimento()) + "\t" +
                            formatadorDeNum.format(funcionario.getSalario()) + "\t" +
                            funcionario.getFuncao()
            );
    }








    //Método responsável por processar o aumento de 10% para os funcionários, recebe a lista de funcionários e usa a função map para atribuir
    //um novo valor ao atributo salário de cada elemento, depois retorna a lista com os salários atualizados
    public List<Funcionario> gerarAumento (List<Funcionario> funcionarios){

        List<Funcionario> funcionariosComAumento = funcionarios.stream()
                .map(funcionario -> {
                    BigDecimal novoSalario = funcionario.getSalario().multiply(new BigDecimal(1.1));
                    funcionario.setSalario(novoSalario);
                    return funcionario;
                })
                .collect(Collectors.toList());

        System.out.println("Aumento gerado com sucesso!\n");
        return funcionariosComAumento;

    }






    //Método auxiliar responsável por agrupar os funcionários por função, esse método não é utilizado pela função Main, porém auxilia um outro
    //método responsável por imprimir os funcionários agrupados.
    //Utiliza uma estrutura HashMap e coloca o atributo função como chave e uma lista de funcionários que atuam naquela função como valor
    public Map<String, List<Funcionario>> agruparPorFuncao(List<Funcionario> funcionarios){

        Map<String, List<Funcionario>> funcionariosPorFuncao = new HashMap<>();

        funcionariosPorFuncao = funcionarios.stream()
                                .collect(Collectors.groupingBy(funcionario -> funcionario.getFuncao()));

        return funcionariosPorFuncao;   //Retorna a estrutura HashMap criada

    }





    //Método responsável por imprimir a lista de funcionários ordenando pelas funções deles
    public void imprimirFuncionarioPorFuncao(List<Funcionario> funcionarios){

        System.out.println("=============================================");

        Map<String, List<Funcionario>> lista = new HashMap<String, List<Funcionario>>();

        lista = this.agruparPorFuncao(funcionarios); //chama o método que agrupa e retorna o HashMap

        lista.forEach((funcao, listaFuncionarios) -> {          //Faz um foreach colocando a função e abaixo a lista de funcionários

            System.out.println(funcao);
            for (Funcionario funcionario : listaFuncionarios){
                imprimirFuncionario(funcionario);
            }

            System.out.println("=============================================");

        });

    }






    //Método responsável por imprimir uma lista de funcionários que fazem aniversário em um determinado mês
    //Ele recebe a lista completa de funcionários e um número inteiro referente ao mês em questão
    public void imprimirFuncionariosPorMesDeAniversario(List<Funcionario> funcionarios, int mes){

        List<Funcionario> aniversariantes = new ArrayList<>();

        for(Funcionario funcionario : funcionarios) {

            //Código para converter o atributo dataNascimento de LocalDate para Date, assim é possível extrair um número referente ao mês
            //daquela data
            Date data = Date.from(funcionario.getDataNascimento().atStartOfDay(ZoneId.systemDefault()).toInstant());
            GregorianCalendar dataCalendario = new GregorianCalendar();
            dataCalendario.setTime(data);

            int aniversarioFuncionario = dataCalendario.get(Calendar.MONTH) + 1;    //registra o número do mês adicionando 1 pois é um array

            if (aniversarioFuncionario == mes){
                aniversariantes.add(funcionario);
            }                                       //Verifica se é o mesmo e mostra a lista

        }

        for (Funcionario funcionario : aniversariantes){
            imprimirFuncionario(funcionario);
        }
                //Caso não haja nenhum aniversariante do mês, mostra essa mensagem
        if (aniversariantes.isEmpty()) System.out.println("Não há funcionários que fazem aniversário nesse mês!");

    }






    //Método responsável por imprimir nome e data de nascimento do funcionário com maior idade da lista
    public void imprimirFuncionarioComMaiorIdade(List<Funcionario> funcionarios){   //recebe lista de funcionários

        LocalDate dataMaisVelha = LocalDate.now();
        Funcionario funcionarioComMaiorIdade = new Funcionario("a", LocalDate.now(), new BigDecimal(0), "b");

        for (Funcionario funcionario : funcionarios){                           //Faz um foreach na lista e comparando as idades
            if (funcionario.getDataNascimento().isBefore(dataMaisVelha)){
                dataMaisVelha = funcionario.getDataNascimento();
                funcionarioComMaiorIdade = funcionario;
            }
        }

        DateTimeFormatter formatadorDeData = DateTimeFormatter.ofPattern("dd/MM/yyyy");  //Formatador para mostrar data no estilo brasileiro

        System.out.println(
                funcionarioComMaiorIdade.getNome() + "\t"
                        +formatadorDeData.format(funcionarioComMaiorIdade.getDataNascimento())
        );

    }








    //Método responsável por imprimir lista de funcionários em ordem alfabética, recebe apenas a lista de funcionários
    public void imprimirFuncionariosEmOrdemAlfabetica(List<Funcionario> funcionarios){

        funcionarios.sort(Comparator.comparing(funcionario -> funcionario.getNome()));  //função comparing utiliza algoritmo sort usando como
        for (Funcionario funcionario : funcionarios){                                   //base o atributo passado, no caso atributo nome
            imprimirFuncionario(funcionario);
        }

    }








    //Método responsável por imprimir o total de salários dos funcionários
    public void imprimirTotalDeSalarios(List<Funcionario> funcionarios){

        DecimalFormat formatadorDeNum = new DecimalFormat("###,###.##"); //Formatador de BigDecimal para mostrar os dados de maneira certa
        BigDecimal totalSalario = new BigDecimal(0);

        for(Funcionario funcionario : funcionarios){
            totalSalario = totalSalario.add(funcionario.getSalario());      //Vai passando pelos elementos e somando os valores
        }

        System.out.println("Total de salários dos funcionários: R$" + formatadorDeNum.format(totalSalario));
    }                                                                   //Mostra o valor final formatado









    //Imprimir responsável por mostrar quantos salários mínimos cada funcionário ganha, contando que o salário mínimo seja R$1212,00
    public void imprimirSalariosMinimos (List<Funcionario> funcionarios){

        Map<String, BigDecimal> salariosMinimos = new HashMap<String, BigDecimal>(); //Map que terá como chave o nome e como valor
                                                                                     // a quantidade de salários
        funcionarios.forEach(funcionario -> {

            BigDecimal minimo = new BigDecimal(1212);
            BigDecimal qtdMinimos = new BigDecimal(0);
            qtdMinimos = funcionario.getSalario().divide(minimo, 2, RoundingMode.DOWN); //Divide o salário por 1212

            salariosMinimos.put(funcionario.getNome(), qtdMinimos);     //Adiciona a chave e o valor no Map

        });

        salariosMinimos.forEach(((nome, quantidade) -> {
            System.out.println(nome + " ganha " + quantidade + " salários mínimos");    //Mostra os dados
        }));

    }





}
