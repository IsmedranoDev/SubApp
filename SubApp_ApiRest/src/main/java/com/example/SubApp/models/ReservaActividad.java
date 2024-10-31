package com.example.SubApp.models;

public class ReservaActividad {

    private String Nombre;
    private String Apellidos;
    private int NumeroBuceos;
    private int CapacidadBotella;
    private String ConexionBotella;


    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public int getNumeroBuceos() {
        return NumeroBuceos;
    }

    public void setNumeroBuceos(int numero_buceos) {
        NumeroBuceos = numero_buceos;
    }

    public int getCapacidadBotella() {
        return CapacidadBotella;
    }

    public void setCapacidadBotella(int capacidadBotella) {
        CapacidadBotella = capacidadBotella;
    }

    public String getConexionBotella() {
        return ConexionBotella;
    }

    public void setConexionBotella(String conexionBotella) {
        ConexionBotella = conexionBotella;
    }
}