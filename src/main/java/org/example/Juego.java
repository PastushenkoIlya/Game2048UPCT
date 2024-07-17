package org.example;

public class Juego {
    private Estado estado;

    public Juego() {
        estado = new Estado();
    }

    public void nuevoJuego() {
        estado.iniciar();
        estado.agregarValor();
        estado.agregarValor();
    }

    public boolean finJuego() {
        if (estado.contarCeros() == 0 && !estado.esPosibleSumar()) {
            return true;
        }
        return false;
    }

    public void jugarIzquierda() {
        estado.moverIzquierda();
        estado.agregarValor();
    }

    public void jugarDerecha() {
        estado.moverDerecha();
        estado.agregarValor();
    }

    public void jugarArriba() {
        estado.moverArriba();
        estado.agregarValor();
    }

    public void jugarAbajo() {
        estado.moverAbajo();
        estado.agregarValor();
    }

    public long[][] getTablero() {
        return estado.getTablero();
    }
    public long puntos() {
        return estado.puntos();
    }
}