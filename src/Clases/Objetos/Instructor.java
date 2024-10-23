/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases.Objetos;

import Clases.Objetos.Buceador;
import java.util.Date;

/**
 *
 * @author israelmedrano
 */
public class Instructor extends Buceador{
    
    String idiomas;
    
    
    public Instructor(int Id,String nombre, String apellidos,Date fechaNacimiento, String direccion, String telefono, String email, String certificaciones, String idiomas){
        
        super(Id, nombre, apellidos, fechaNacimiento, direccion, telefono, email, certificaciones);
    
        
        this.idiomas=idiomas;
    
    }

  

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idioma) {
        this.idiomas = idioma;
    }
    
    
    
    
}
