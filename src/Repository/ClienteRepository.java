/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Clases.Objetos.Actividad;
import Clases.Objetos.Cliente;
import Clases.Objetos.Instructor;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;

/**
 *
 * @author israelmedrano
 */
public abstract class ClienteRepository {
    
  
     public abstract boolean registraCliente(Cliente a);
    
    /**
     *
     * @param a
     */
    public abstract void  getAllCliente(DefaultTableModel a);
    
    public abstract boolean registraInstructor(Instructor a);
    
    public abstract Map<String, Integer> cargaInstructores();
    
    public abstract boolean registrar_salidas_buceo(Actividad a);
    
    public abstract void consultaRerservas(DefaultTableModel modelo, String fecha);
    
    public abstract void busquedaClientes(DefaultTableModel model, String nombre);
    
    public abstract int agregarClienteActividad(int actividad, int cliente, int botella);
    
    public abstract boolean estaClienteEnActividad (int salidaId, int clienteId);
    
    public abstract void cargarReservas(DefaultTableModel modelo, int id);
    
    public abstract Map<String, List<Object[]>> trabajoDiario(String fecha);
    
    public abstract String formatoFecha(Date date);
    
 
    
}
