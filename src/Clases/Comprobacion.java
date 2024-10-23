/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Component;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author israelmedrano
 */
public class Comprobacion {
    
    /**
     * Comprueba si un campo de texto está vacío
     * @param campo
     * @return devuelve TRUE si está vacio
     */
     public static boolean vacio (JTextField campo) {
        return "".equals(campo.getText().trim());
    }
    /**
     * Crea una alarma en una JPanel de error
     * @param padre
     * @param campo 
     */
    public static void alertaVacio (Component padre, JTextField campo) {
        JOptionPane.showMessageDialog(padre, "El campo " +campo.getName()+ " no puede quedar vacío", "Campo obligatorio", JOptionPane.ERROR_MESSAGE);
        campo.setBackground(Color.red);
    }
/**
 * Comprueba que la selección es distinta de 0, si lo es, devuelve true
 * @param select
 * @return 
 */
   public static boolean select (JComboBox select) {
        return select.getSelectedIndex() == 0;
    }
    /**
     * Crea una alarma en un JPanel advirtiende de que hay que seleccionar algo distinto al primer valor del Selector
     * @param padre
     * @param select 
     */
    public static void alertaSelect (Component padre, JComboBox select) {
        JOptionPane.showMessageDialog(padre, "Debe seleccionar un elemento del campo " +select.getName(), "Campo obligatorio", JOptionPane.ERROR_MESSAGE);
        
    }
    
    public static boolean fechaVacia(JDateChooser fecha){
        return fecha.getDate()==null;
    }
    
    public static void alertaFechaVacia (Component padre, JDateChooser fecha){
        JOptionPane.showMessageDialog(padre, "Se debe seleccionar una fecha en el campo" + fecha.getName(), "Campo obligatorio", JOptionPane.ERROR_MESSAGE);
        fecha.getDateEditor().getUiComponent().setBackground(Color.red);
        
    }
    
    /**
     * Método para comprobar que el patrón del email es correcto
     */
    private static final String EMAIL_PATTERN
            = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

    /**
     * 
     * @param email
     * @return  Falso si el email es nulo, Verdadero si el patrón coincide
     */
    public static boolean esEmail(String email) {
        Pattern patron = Pattern.compile(EMAIL_PATTERN);
         Matcher coincide = patron.matcher(email);
        
        if (coincide.matches()) return true;
            else return false;
       

    }
    
    public static void alertaEmail(Component padre, JTextField campo){
        JOptionPane.showMessageDialog(padre, "Debe introducir un email válido", "Email requerido", JOptionPane.ERROR_MESSAGE);
    }
    
    
    public static boolean esNumero(String numero){
        try{
            int n = Integer.parseInt(numero);
            return true;
            
        }catch(NumberFormatException ex){
            return false;
        }
        
    }
    
    public static void alertaNumero(Component padre, JTextField campo){
        JOptionPane.showMessageDialog(padre, "El campo" + campo.getName() + " debe contener un número válido", "Número inválido", JOptionPane.ERROR_MESSAGE);
        campo.setBackground(Color.red);
    }
    
    public static boolean esTelefono(String telefono){
        
        Pattern patron = Pattern.compile("^\\+?[0-9. ()-]{7,}$");
        Matcher coincide = patron.matcher(telefono);
        if(coincide.matches()) return true;
        else return false;
    }
    
    public static void alertaTelefono(Component padre, JTextField campo){
        JOptionPane.showMessageDialog(padre, "El campo" + campo.getName() + " debe contener un número de teléfono válido" , "Teléfono inválido", JOptionPane.ERROR_MESSAGE);
        campo.setBackground(Color.red);
        
        
    }
}
