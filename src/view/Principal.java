package view;

import controller.ThreadCarro;
import controller.ThreadCruzamento;

import java.util.concurrent.Semaphore;

public class Principal {
    public static void main(String[] args) {
        ex_cruzamento();
    }

    // PG 37
    public static void ex_cruzamento() {
        int permissoes = 1;
        int carros = 8;
        Semaphore semaforo = new Semaphore(permissoes);
        for (int idCarro = 0; idCarro < carros; idCarro++) {
            Thread tCruzamento = new ThreadCruzamento(idCarro, semaforo);
            tCruzamento.start();
        }
    }

    // Exemplo Aula
    public static void ex_carro() {
        int permissoes = 3;
        Semaphore semaforo = new Semaphore(permissoes);

        for (int idCarro = 0; idCarro < 10; idCarro++) {
            Thread tCarro = new ThreadCarro(idCarro, semaforo);
            tCarro.start();
        }
    }
}
