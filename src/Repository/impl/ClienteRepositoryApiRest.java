/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.impl;

import Clases.Objetos.Actividad;
import Clases.Objetos.Cliente;
import Clases.Objetos.Instructor;
import Repository.ClienteRepository;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author israelmedrano
 */
public class ClienteRepositoryApiRest extends ClienteRepository {
    
    //URL de la API REST 
   

    @Override
    public boolean registraCliente(Cliente a) {
        
             try {
           //Establezco la conexión
           ConexionAPI conexion = new ConexionAPI();
           HttpURLConnection conn  = conexion.abrirConexion("POST", "clientes");
           
           
           //Convierto el cliente a tipo json
           ObjectMapper clienteMapeado = new ObjectMapper();
           String json = clienteMapeado.writeValueAsString(a);
           
           
           //Envío el json
           try(OutputStream os = conn.getOutputStream()){
               byte[] input = json.getBytes("utf-8");
               os.write(input, 0, input.length);
           }
           
           
           //Leo la respuesta
           try (BufferedReader br = new BufferedReader ( new InputStreamReader(conn.getInputStream(), "utf-8"))){
               
               StringBuilder respuesta = new StringBuilder();
               String respuestaLinea;
               while((respuestaLinea = br.readLine()) != null){
                   respuesta.append(respuestaLinea.trim());
               }
               System.out.println(respuesta.toString());
               
           } 
           return true;
           
       } catch (Exception ex) {
           Logger.getLogger(ClienteRepositoryApiRest.class.getName()).log(Level.SEVERE, null, ex);
           return false;
       }
    }

    @Override
    public void getAllCliente(DefaultTableModel a) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean registraInstructor(Instructor a) {
        
    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
           
    }   
   

    @Override
    public Map<String, Integer> cargaInstructores() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean registrar_salidas_buceo(Actividad a) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void consultaRerservas(DefaultTableModel modelo, String fecha) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void busquedaClientes(DefaultTableModel model, String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int agregarClienteActividad(int actividad, int cliente, int botella) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean estaClienteEnActividad(int salidaId, int clienteId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void cargarReservas(DefaultTableModel modelo, int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Map<String, List<Object[]>> trabajoDiario(String fecha) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String formatoFecha(Date date) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    


    
}
