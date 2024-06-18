package conta;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import conta.controller.ContaController;
import conta.model.*;
import conta.util.*;

public class Menu {
    private static Conta novaConta;

    public static void main(String[] args) {

        ContaController contas = new ContaController();

        Scanner leia = new Scanner(System.in);

        int opcao, numero, agencia, tipo, aniversario, numeroDestino;
        String titular;
        float saldo, limite, valor;

        while (true) {

            System.out.println(Cores.TEXT_RED_BOLD + Cores.ANSI_BLACK_BACKGROUND + "*****************************************************");
            System.out.println("                                                     ");
            System.out.println("                BANCO SOLIDEZ                ");
            System.out.println("                                                     ");
            System.out.println("*****************************************************");
            System.out.println("                                                     ");
            System.out.println("            1 - Criar Conta                          ");
            System.out.println("            2 - Listar todas as Contas               ");
            System.out.println("            3 - Buscar Conta por Numero              ");
            System.out.println("            4 - Atualizar Dados da Conta             ");
            System.out.println("            5 - Apagar Conta                         ");
            System.out.println("            6 - Sacar                                ");
            System.out.println("            7 - Depositar                            ");
            System.out.println("            8 - Transferir valores entre Contas      ");
            System.out.println("            9 - Sair                                 ");
            System.out.println("                                                     ");
            System.out.println("*****************************************************");
            System.out.println("Entre com a opção desejada:                          ");
            System.out.println("                                                     ");
            opcao = leia.nextInt();

            if (opcao == 9) {
                System.out.println("\nBanco Solidez - O seu amanhã começa aqui!");
                leia.close();
                System.exit(0);
            }

            switch (opcao) {
                case 1:
                    System.out.println(Cores.TEXT_RED + "\n Criar Conta");
                    System.out.println("Digite o Número da Agência: ");
                    agencia = leia.nextInt();
                    System.out.println("Digite o Nome do Titular: ");
                    leia.skip("\\R?");
                    titular = leia.nextLine();

                    do {
                        System.out.println("Digite o Tipo da Conta (1-CC ou 2-CP): ");
                        tipo = leia.nextInt();
                    } while (tipo < 1 && tipo > 2);

                    System.out.println("Digite o Saldo da Conta (R$): ");
                    saldo = leia.nextFloat();

                    switch (tipo) {
                        case 1 -> {
                            System.out.println("Digite o Limite de Crédito (R$): ");
                            limite = leia.nextFloat();
                            contas.cadastrar(new ContaCorrente(contas.gerarNumero(), agencia, tipo, titular, saldo, limite));
                            System.out.println("A Conta número: " + contas.getNumero() + " foi criada com sucesso!");
                        }
                        case 2 -> {
                            System.out.println("Digite o dia Aniversário da Conta: ");
                            aniversario = leia.nextInt();
                            contas.cadastrar(new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
                            System.out.println("A Conta número: " + contas.getNumero() + " foi criada com sucesso!");
                            {
                            }
                        }
                    }
                case 2:
                    System.out.println(Cores.TEXT_RED + "Listar todas as Contas\n\n");
                    contas.listarTodas();
                    keyPress();
                    break;
                case 3:
                    System.out.println(Cores.TEXT_RED + "Consultar dados da conta - por numero\n\n");
                    System.out.println("Digite o numero da conta: ");
                    numero = leia.nextInt();

                    contas.procurarPorNumero(numero);

                    keyPress();
                    break;
                case 4:
                    System.out.println(Cores.TEXT_RED + "Atualizar dados da Conta\n\n");

                    System.out.println("Digite o número da conta: ");
                    numero = leia.nextInt();

                    var buscaConta = contas.buscarNaCollection(numero);

                    break;
                case 5:
                    System.out.println("\nApagar Conta");
                    System.out.println(Cores.TEXT_RED + "Apagar Conta\n\n");

                    System.out.println("Digite o número da conta: ");
                    numero = leia.nextInt();

                    contas.deletar(numero);
                    keyPress();
                    break;
                case 6:
                    System.out.println("\n Sacar");
                    System.out.println("Digite o numero da conta: ");
                    numero = leia.nextInt();

                    do {
                        System.out.println("Digite o Valor do Saque (R$)");
                        valor = leia.nextFloat();
                    } while (valor <= 0);

                    contas.sacar(numero, valor);

                    keyPress();
                    break;
                case 7:
                    System.out.println("\n Depositar");
                    System.out.println("Digite o numero da conta: ");
                    numero = leia.nextInt();

                    do {
                        System.out.println("Digite o valor do depósito (R$): ");
                        valor = leia.nextFloat();
                    } while (valor <= 0);

                    contas.depositar(numero, valor);
                    keyPress();
                    break;
                case 8:
                    System.out.println("\n Transferir");
                    System.out.println("Digite o numero da conta de origem: ");
                    numero = leia.nextInt();
                    System.out.println("Digite o numero da conta de destino: ");
                    numeroDestino = leia.nextInt();

                    do {
                        System.out.println("Digite o valor de origem (R$): ");
                        valor = leia.nextFloat();
                    }while(valor <= 0);

                    contas.transferir(numero, numeroDestino, valor);
                    keyPress();
                    break;
                default:
                    System.out.println(Cores.TEXT_RED + "\nOpção inválida!\n");
                    keyPress();
            }
        }
    }
    private static void keyPress() {
        try {

            System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para Continuar...");
            System.in.read();

        } catch (IOException e) {

            System.out.println("Você pressionou uma tecla diferente de enter!");
        }
    }
}

