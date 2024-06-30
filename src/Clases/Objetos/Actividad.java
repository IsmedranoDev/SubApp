/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases.Objetos;

import java.util.Date;

/**
 *
 * @author israelmedrano
 */
public class Actividad {
    
    private String fecha;
    private String hora;
    private String lugar;
    private String puntoSalida;
    private int instructor;
    private String tipoSalida;
    
    public Actividad (String fecha, String hora, String lugar, String puntoSalida, int instructor, String tipoSalida){
        
        this.fecha = fecha;
        this.hora = hora;
        this.lugar = lugar;
        this.puntoSalida=puntoSalida;
        this.instructor=instructor;
        this.tipoSalida=tipoSalida;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getPuntoSalida() {
        return puntoSalida;
    }

    public void setPuntoSalida(String puntoSalida) {
        this.puntoSalida = puntoSalida;
    }

    public int getInstructor() {
        return instructor;
    }

    public void setInstructor(int instructor) {
        this.instructor = instructor;
    }

    public String getTipoSalida() {
        return tipoSalida;
    }

    public void setTipoSalida(String tipoSalida) {
        this.tipoSalida = tipoSalida;
    }
    
}
