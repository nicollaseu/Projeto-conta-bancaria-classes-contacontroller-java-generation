package conta.controller;

import java.util.ArrayList;
import java.util.List;

import conta.repository.ContaRepository;
import conta.model.Conta;

public class ContaController implements ContaRepository {

    private ArrayList<Conta> listaContas = new ArrayList<Conta>();
    int numero = 0;

    public List<Conta> listarContas() {
        for (var conta : listaContas) conta.visualizar();
        return null;
    }

    @Override
    public void procurarPorNumero(int numero) {
        var conta = buscarNaCollection(numero);

        if (conta != null)
            conta.visualizar();
        else
            System.out.println("\nA Conta número: " + numero + " não foi encontrada! ");
    }

    @Override
    public void listarTodas() {
        for(var conta :listaContas) {
            conta.visualizar();
        }
    }

    public void cadastrar(Conta conta) {
        listaContas.add(conta);
        System.out.println("\nA Conta número: " + conta.getNumero() + " foi criada com sucesso!");
    }

    @Override
    public void atualizar(Conta conta) {
        var buscaConta = buscarNaCollection(conta.getNumero());
        if (buscaConta != null) {
            listaContas.set(listaContas.indexOf(buscaConta), conta);
            System.out.println("\nA conta numero: " +conta.getNumero() + " foi atualizada com sucesso!");
        } else
            System.out.println("\nA conta numero: " +conta.getNumero() + " Não foi encontrada!");
    }

    @Override
    public void deletar(int numero) {
        var conta = buscarNaCollection(numero);

        if (conta != null) {
            if (listaContas.remove(conta) == true) {
                System.out.println("\nA conta numero: " + numero + " foi deletada com sucesso!");
            }
        } else {
            System.out.println("\nA conta numero: " + numero + " Não foi encontrada!");
        }
    }

    @Override
    public void sacar(int numero, float valor) {
        var conta = buscarNaCollection(numero);

        if (conta != null) {
            if (conta.sacar(valor) == true) {
                System.out.println("\nO saque na conta numero: " + numero + " foi sacado com sucesso!");
            }
        }else {
            System.out.println("\nA conta numero: " +numero+ " não foi encontrada!");
        }
    }

    @Override
    public void depositar(int numero, float valor) {
        var conta = buscarNaCollection(numero);
        if (conta != null) {
            conta.depositar(valor);
            System.out.println("\nO depósito na conta numero: " + numero + " foi depositado com sucesso!");
        }else{
            System.out.println("\nA conta numero: " +numero+ " não foi encontrada ou a conta destino não é uma conta corrente!");
        }
    }

    @Override
    public void transferir(int numeroOrigem, int numeroDestino, float valor) {
        var contaOrigem = buscarNaCollection(numeroOrigem);
        var contaDestino = buscarNaCollection(numeroDestino);

        if (contaOrigem != null && contaDestino != null) {
            if (contaOrigem.sacar(valor) == true){
                contaDestino.depositar(valor);
                System.out.println("\nA transferência foi efetuada com sucesso!");

            }
        }else {
            System.out.println("\nA conta de origem e/ou destino não foram encontradas!");
        }
    }

    public int getNumero() {
        return numero;
    }

    public int gerarNumero() {
        return ++numero;
    }

    public Conta buscarNaCollection(int numero) {
        for (var conta : listaContas) {
            if (conta.getNumero() == numero ) {
                return conta;
            }
        }
        return null;
    }
}
