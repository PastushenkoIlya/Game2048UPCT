package org.example;


public class Record {
    private String nombre;
    private long puntos;

    public Record(String nombre, long puntos) {
        this.nombre = nombre;
        this.puntos = puntos;
    }

    public String getNombre() {
        return nombre;
    }

    public long getPuntos() {
        return puntos;
    }
    @Override
    public String toString() {
        return nombre + ": " + puntos;
    }
}
