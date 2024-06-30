
package Clases.Objetos;

/**
 *
 * @author israelmedrano
 */
import java.util.Date;

public class Cliente extends Buceador {
    // Atributos de la clase
   
    
    private int numeroBuceos;
    private String nivel;

    // Constructor
    public Cliente(String nombre, String apellidos,Date fechaNacimiento, String direccion, String telefono, String email, String certificaciones, int numeroBuceos, String nivel) {
       
        super(nombre, apellidos, fechaNacimiento, direccion, telefono, email, certificaciones);
        
        this.nivel=nivel;
        this.numeroBuceos=numeroBuceos;
        
       
    }
    
    // Getters y setters 

  

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public int getNumeroBuceos() {
        return numeroBuceos;
    }

    public void setNumeroBuceos(int numeroBuceos) {
        this.numeroBuceos = numeroBuceos;
    }
  


 
}

