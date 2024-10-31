/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.impl;


import Clases.Objetos.Actividad;
import Clases.Objetos.Cliente;
import Clases.Objetos.Instructor;
import Repository.ClienteRepository;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author israelmedrano
 */
public class RepositoryMySQL extends ClienteRepository{
    
    static String url = "jdbc:mysql://localhost:3306/SubApp";
    static Connection conn;
    String user = "root";
    String pass = "";
    public  Connection conectar(){
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            conn = DriverManager.getConnection(url, user, pass);
            
            return conn;
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RepositoryMySQL.class.getName()).log(Level.SEVERE, "Error al cargar el driver de MySQL", ex);
            JOptionPane.showMessageDialog(null, "No se pudo cargar el driver de MySQL\n" + ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(RepositoryMySQL.class.getName()).log(Level.SEVERE, "Error al conectar a la base de datos", ex);
            JOptionPane.showMessageDialog(null, "No se ha podido conectar con la base de datos\n" + ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean registraCliente(Cliente a) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void getAllCliente(DefaultTableModel model) {
        
             Object[] cliente = new Object[5];
        String sql="SELECT id, nombre, apellidos, numero_buceos, certificaciones\n" +
                    "FROM clientes\n";
        conectar();
        try{
            
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet consulta = pst.executeQuery();
            
            
            while(consulta.next()){
                cliente[0] = consulta.getInt("id");
                cliente[1] = consulta.getString("nombre");
                cliente[2] = consulta.getString("apellidos");
                cliente[3] = consulta.getInt("numero_buceos");
                cliente[4]= consulta.getString("certificaciones");
                model.addRow(cliente);
            }
        }catch (SQLException ex) {
            Logger.getLogger(RepositorySQLite.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        
           


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
     String sql = "SELECT c.nombre, c.apellidos, b.capacidad, b.conexion\n" +
                    "FROM actividades a\n" +
                    "JOIN clientes c ON a.cliente_id = c.id\n" +
                    "JOIN botellas b ON a.botellas_id = b.id\n" +
                    "WHERE a.salida_id = " + id + ";";
        Object[] cliente = new Object[4];
        
        conectar();
        try{
            ResultSet consulta = conn.prepareStatement(sql).executeQuery();
           
            while(consulta.next()){
                cliente[0] = consulta.getString("nombre");
                cliente[1] = consulta.getString("apellidos");
                cliente[2] = consulta.getInt("capacidad");
                cliente[3] = consulta.getString("conexion");
                modelo.addRow(cliente);
            }
        }catch (SQLException ex) {
            Logger.getLogger(RepositorySQLite.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    
}

    @Override
    public Map<String, List<Object[]>> trabajoDiario(String fecha) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String formatoFecha(Date date) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    public void cerrarConexion() {
        try {
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se ha podido cerrar la conexión a la base de datos\n" + ex.getMessage());
        }
        
    }
    
    
    
}
