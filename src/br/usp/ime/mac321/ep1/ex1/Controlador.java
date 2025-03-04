package br.usp.ime.mac321.ep1.ex1;

import java.util.LinkedList; 


public class Controlador {
    private LinkedList<Evento> eventos = new LinkedList<>();

    public void insere(Evento e) {
        eventos.add(e);
    }

    public void run() {
        while (!eventos.isEmpty()) {
            Evento e = eventos.pop();
            if (e.pronto()) {
                e.ação();
                System.out.print(e.descrição() + "\n");
            } else {
                eventos.add(e);
            }
        }
    }
}
