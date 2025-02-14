package org.example;

import org.example.Entity.Funcionario;
import org.example.Service.Operacoes;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        /*
        Classe main que irá executar as ações pedidas utilizando os métodos contidos na classe operações
        Esse código abaixo refere-se a 1º forma de uso, onde ao ser executado, irá gerar um menu em console onde será possível executar as
        ações, caso o avaliador não queira utilizar o menu, pode comentar o código todo e descomentar o código abaixo que contém uma versão
        simplificada que executa todos os métodos da mesma forma porém de uma vez só, sem necessidade de ficar digitando.

        */

        Scanner leitura = new Scanner(System.in);           //Scanner para ler os inputs do usuário

        int resposta;                                       //Variável que irá conter o valor do input

        Operacoes operacoes = new Operacoes();              //Instância da classe de operação

        List<Funcionario> funcionarios = new ArrayList<>(); //Criação da lista

        System.out.println("Cadastro de Funcionários --------------------------------\n");

        //Aqui adicionei manualmente os funcionários da lista, pois como são muitos, achei que seria útil.

        operacoes.inserirFuncionario(funcionarios, "Maria", "18/10/2000",
                2009.44, "Operador");

        operacoes.inserirFuncionario(funcionarios, "João", "12/05/1990",
                2284.38, "Operador");

        operacoes.inserirFuncionario(funcionarios, "Caio", "02/05/1961",
                9836.14, "Coordenador");

        operacoes.inserirFuncionario(funcionarios, "Miguel", "14/10/1988",
                19119.88, "Diretor");

        operacoes.inserirFuncionario(funcionarios, "Alice", "05/01/1995",
                2234.68, "Recepcionista");

        operacoes.inserirFuncionario(funcionarios, "Heitor", "19/11/1999",
                1582.72, "Operador");

        operacoes.inserirFuncionario(funcionarios, "Arthur", "31/03/1993",
                4071.84, "Contador");

        operacoes.inserirFuncionario(funcionarios, "Laura", "08/07/1994",
                3017.45, "Gerente");

        operacoes.inserirFuncionario(funcionarios, "Heloisa", "24/05/2003",
                1606.85, "Eletricista");

        operacoes.inserirFuncionario(funcionarios, "Helena", "02/09/1996",
                2799.93, "Gerente");

        System.out.println("Bem vindo ao menu de gerenciamento de funcionários!");

        do {                                                                           //Estrutura do Menu
            System.out.println("\nPor favor digite a operação desejada: \n" +
                               "1 - Inserir novo funcionário. \n" +
                               "2 - Remover funcionário. \n" +
                               "3 - Imprimir lista de funcionários. \n" +
                               "4 - Gerar aumento para funcionários. \n" +
                               "5 - Imprimir lista de funcionários agrupados por função. \n" +
                               "6 - Imprimir funcionários a partir do mês de aniversário. \n" +
                               "7 - Imprimir funcionário com maior idade. \n" +
                               "8 - Imprimir lista de funcionários em ordem alfabética. \n" +
                               "9 - Imprimir total de salários de funcionários. \n" +
                               "10 - Imprimir quantos salários mínimos cada funcionário ganha. \n" +
                               "0 - Sair do menu.");

            resposta = leitura.nextInt();               //Leitura do valor

            switch (resposta){                          //Estrutura Switch-Case para tratar as possibilidades de resposta

                case 1:
                    System.out.println("Por favor digite o nome do novo funcionário: ");
                    String nome = leitura.next();
                    System.out.println("Por favor digite a data de nascimento do novo funcionário, com barras (/) por favor: ");
                    String nascimento = leitura.next();
                    System.out.println("Por favor digite o salário do novo funcionário: ");
                    Double salario = leitura.nextDouble();
                    System.out.println("Por favor digite a função do novo funcionário: ");
                    String funcao = leitura.next();

                    operacoes.inserirFuncionario(funcionarios, nome, nascimento, salario, funcao);
                    continue;

                    //Caso o usuário digite a data de nascimento colocando '.' na parte de ano, irá dar erro, ou caso ele troque a pontuação
                    //dos valores de salário, colocando vírgula para milhares e ponto para casas decimais

                case 2:
                    System.out.println("Por favor digite o nome do funcionário que será removido.");
                    String funRemovido = leitura.next();
                    operacoes.removerFuncionario(funcionarios, funRemovido);
                    continue;

                    //Caso tenha mais de um funcionário com o mesmo nome, ele irá apagar o primeiro que aparecer no loop

                case 3:
                    for (Funcionario funcionario : funcionarios) operacoes.imprimirFuncionario(funcionario);
                    continue;

                case 4:
                    operacoes.gerarAumento(funcionarios);
                    for (Funcionario funcionario : funcionarios) operacoes.imprimirFuncionario(funcionario);
                    continue;

                case 5:
                    operacoes.imprimirFuncionarioPorFuncao(funcionarios);
                    continue;

                case 6:
                    System.out.println("Digite o número do mês que deseja conferir os aniversariantes:");
                    int mes = leitura.nextInt();
                    operacoes.imprimirFuncionariosPorMesDeAniversario(funcionarios, mes);
                    continue;

                case 7:
                    operacoes.imprimirFuncionarioComMaiorIdade(funcionarios);
                    continue;

                case 8:
                    operacoes.imprimirFuncionariosEmOrdemAlfabetica(funcionarios);
                    continue;

                case 9:
                    operacoes.imprimirTotalDeSalarios(funcionarios);
                    continue;

                case 10:
                    operacoes.imprimirSalariosMinimos(funcionarios);
                    continue;

            }

        } while (resposta != 0 && resposta <= 10);

        /*
        Sempre que a tarefa do usuário for completada, irá voltar ao menu, só irá terminar caso o usuário digite 0, um número diferente
        das opções disponíveis ou se caso o funcionário digite valores que não são números.
        */

        /*Este é o código disponível caso não queira usar o menu, ele executa os mesmos métodos porém tudo de uma vez sem a ajuda do menu*/


        /*

        Operacoes operacoes = new Operacoes();

        List<Funcionario> funcionarios = new ArrayList<>();

        System.out.println("Cadastro de Funcionários --------------------------------\n");

        operacoes.inserirFuncionario(funcionarios, "Maria", "18/10/2000",
                2009.44, "Operador");

        operacoes.inserirFuncionario(funcionarios, "João", "12/05/1990",
                2284.38, "Operador");

        operacoes.inserirFuncionario(funcionarios, "Caio", "02/05/1961",
                9836.14, "Coordenador");

        operacoes.inserirFuncionario(funcionarios, "Miguel", "14/10/1988",
                19119.88, "Diretor");

        operacoes.inserirFuncionario(funcionarios, "Alice", "05/01/1995",
                2234.68, "Recepcionista");

        operacoes.inserirFuncionario(funcionarios, "Heitor", "19/11/1999",
                1582.72, "Operador");

        operacoes.inserirFuncionario(funcionarios, "Arthur", "31/03/1993",
                4071.84, "Contador");

        operacoes.inserirFuncionario(funcionarios, "Laura", "08/07/1994",
                3017.45, "Gerente");

        operacoes.inserirFuncionario(funcionarios, "Heloisa", "24/05/2003",
                1606.85, "Eletricista");

        operacoes.inserirFuncionario(funcionarios, "Helena", "02/09/1996",
                2799.93, "Gerente");



        System.out.println("Remoção de funcionário----------------------------------------------\n");


        operacoes.removerFuncionario(funcionarios, "João");


        System.out.println("Impressão de Funcionários--------------------------------------------\n");


        for (Funcionario funcionario : funcionarios) operacoes.imprimirFuncionario(funcionario);


        System.out.println("\nAumento de salário de funcionários-------------------------------------\n");


        operacoes.gerarAumento(funcionarios);


        for (Funcionario funcionario : funcionarios) operacoes.imprimirFuncionario(funcionario);


        System.out.println("\nImpressão de funcionários por função------------------------------------\n");


        operacoes.imprimirFuncionarioPorFuncao(funcionarios);


        System.out.println("\nImpressão de funcionários que fazem aniversário nos meses 10 e 12 ------\n");


        operacoes.imprimirFuncionariosPorMesDeAniversario(funcionarios, 10);
        operacoes.imprimirFuncionariosPorMesDeAniversario(funcionarios, 12);


        System.out.println("\nImpressão de funcionário com maior idade---------------------------------\n");


        operacoes.imprimirFuncionarioComMaiorIdade(funcionarios);


        System.out.println("\nImpressão de todos os funcionários em ordem alfabética-------------------\n");


        operacoes.imprimirFuncionariosEmOrdemAlfabetica(funcionarios);


        System.out.println("\nImpressão do total de salários dos funcionários---------------------------\n");


        operacoes.imprimirTotalDeSalarios(funcionarios);


        System.out.println("\nImpressão de quantos salários mínimos ganha cada funcionário---------------\n");


        operacoes.imprimirSalariosMinimos(funcionarios);*/


    }
}

