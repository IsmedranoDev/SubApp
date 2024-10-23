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
    
    private int id;
    private String fecha;
    private String hora;
    private String lugar;
    private String puntoSalida;
    private int instructor_id;
    private String tipoSalida;
    private String instructorNombre;
    
    public Actividad (int id, String fecha, String hora, String lugar, String puntoSalida, int instructor_id, String instructorNombre, String tipoSalida){
        
        this.fecha = fecha;
        this.hora = hora;
        this.lugar = lugar;
        this.puntoSalida=puntoSalida;
        this.instructor_id=instructor_id;
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

    public int getInstructor_id() {
        return instructor_id;
    }

    public void setInstructor_id(int instructor_id) {
        this.instructor_id = instructor_id;
    }

    public String getTipoSalida() {
        return tipoSalida;
    }

    public void setTipoSalida(String tipoSalida) {
        this.tipoSalida = tipoSalida;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInstructorNombre() {
        return instructorNombre;
    }

    public void setInstructorNombre(String instructorNombre) {
        this.instructorNombre = instructorNombre;
    }
    
    
    
    
    
    public Actividad(){
        
    }
}
