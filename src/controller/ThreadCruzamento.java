package controller;

import java.util.concurrent.Semaphore;

public class ThreadCruzamento extends Thread {
    private int idCarro;
    private Semaphore semaforo;

    public ThreadCruzamento(int idCarro, Semaphore semaforo) {
        this.idCarro = idCarro;
        this.semaforo = semaforo;
    }

    @Override
    public void run() {
        carroAndando();
        try {
            semaforo.acquire();
            carroEstacionado();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaforo.release();
            carroSaindo();
        }
    }

    private void carroAndando() {
        int distanciaTotal = (int) (Math.random() * 300) + 200;
        int distanciaPercorrida = 0;
        int deslocamento = 75;
        int tempo = 30;
        while (distanciaPercorrida < distanciaTotal) {
            if (distanciaTotal <= (distanciaPercorrida + deslocamento)) {
                deslocamento = distanciaTotal - distanciaPercorrida;
            }
            distanciaPercorrida += deslocamento;
            try {
                sleep(tempo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(String.format(ConsoleColors.YELLOW_BRIGHT + "#%s andou %sm. e faltam %sm." + ConsoleColors.RESET, this.idCarro, deslocamento, (distanciaTotal - distanciaPercorrida)));
        }
    }

    private void carroEstacionado() {
        System.out.println(ConsoleColors.RED_BRIGHT + "#" + idCarro + " estacionou" + ConsoleColors.RESET);
        int tempo = (int) ((Math.random() * 401) + 100);
        try {
            sleep(tempo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void carroSaindo() {
        String lado;
        if (this.idCarro % 4 == 0) {
            lado = "pela direita";
        } else if (this.idCarro % 3 == 0) {
            lado = "por cima";
        } else if (this.idCarro % 2 == 0) {
            lado = "por baixo";
        } else {
            lado = "pela esquerda";
        }
        System.out.println(String.format(ConsoleColors.GREEN_BRIGHT + "#%s saiu %s" + ConsoleColors.RESET, this.idCarro, lado));
    }
}
