package controller;

import java.util.concurrent.Semaphore;

public class ThreadCarro extends Thread {
    private int idCarro;
    private static int posicaoChegada;
    private static int posicaoSaida;
    private Semaphore semaforo;


    public ThreadCarro(int idCarro, Semaphore semaforo) {
        this.idCarro = idCarro;
        this.semaforo = semaforo;
    }

    @Override
    public void run() {
        carroAndando();
        //--------P (Acquire)----------
        try {
            semaforo.acquire();
            carroEstacionado();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //--------V (Release)----------
            semaforo.release();
            carroSaindo();
        }
    }

    private void carroAndando() {
        int distanciaTotal = (int) ((Math.random() * 501) + 1500);
        int distanciaPercorrida = 0;
        int deslocamento = 100;
        int tempo = 30;
        while (distanciaPercorrida < distanciaTotal) {
            distanciaPercorrida += deslocamento;
            try {
                sleep(tempo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("#" + idCarro + " andou " + distanciaPercorrida + " m.");
        }
        posicaoChegada++;
        System.out.println("#" + idCarro + " foi o " + posicaoChegada + "o. a chegar");
    }

    private void carroEstacionado() {
        System.out.println("#" + idCarro + " estacionou");
        int tempo = (int) ((Math.random() * 401) + 100);
        try {
            sleep(tempo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void carroSaindo() {
        posicaoSaida++;
        System.out.println("#" + idCarro + " foi o " + posicaoSaida + "o. a sair");
    }
}
