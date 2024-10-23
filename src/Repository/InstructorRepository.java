/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Clases.Objetos.Instructor;
import java.util.Map;



/**
 *
 * @author israelmedrano
 */
public abstract class InstructorRepository {
    
    
    public abstract boolean registraInstructor(Instructor instructor);
    
   
public abstract Map<String, Integer> cargaInstructores();   
   
    
}
