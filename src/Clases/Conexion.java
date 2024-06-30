/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import Clases.Objetos.Instructor;
import Clases.Objetos.Actividad;
import Clases.Objetos.Cliente;
import Interfaces.TrabajoDiario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

/**
 * Clase donde desarrollo las principales funciones que usaré en la aplicación
 *
 * @author israelmedrano
 */
public class Conexion {

    // Creo el objeto de la clase conexión y la ruta de acceso a la BD
    static String url = "jdbc:sqlite:taba.db";
    static Connection conn;

    /**
     * Método para establecer la conexión a la base de datos sqLite
     *
     * @return
     */
    public static Connection conectar() {

        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No se ha podido conectar con la base de datos\n" + ex.getMessage());

        }

        return null;

    }

    /**
     * Método para cerrar la conexión a la base de datos
     */
    public static void cerrarConexion() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No se ha podido cerrar la conexión a la base de datos\n" + ex.getMessage());
        }
    }

    /**
     * Método para registrar clientes
     *
     * @param a Recibe un objeto de tipo cliente
     * @return devuelve un booleano true si todo ha ido bien
     */
    public static boolean registraCliente(Cliente a) {

        String sql = "INSERT INTO Clientes ( nombre, apellidos, fecha_nacimiento, direccion, telefono, email, numero_buceos, certificaciones, nivel ) VALUES (?,?,?,?,?,?,?,?,?)";

        boolean registrado;

        //convierto la variable de la fecha para poder registrarla
        java.sql.Date sqlFecha = new java.sql.Date(a.getFechaNacimiento().getTime());

        //java.sql.Date SqlFecha = new java.sql.Date(fecha.getTime());
        Conexion.conectar();
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, a.getNombre());
            pst.setString(2, a.getApellidos());
            pst.setDate(3, sqlFecha);
            pst.setString(4, a.getDireccion());
            pst.setString(5, a.getTelefono());
            pst.setString(6, a.getEmail());
            pst.setInt(7, a.getNumeroBuceos());
            pst.setString(8, a.getCertificaciones());
            pst.setString(9, a.getNivel());

            pst.execute();

            pst.close();

            registrado = true;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            registrado = false;

        } finally{
            Conexion.cerrarConexion();
        }

        return registrado;
    }

    /**
     * Método para registrar Instructores
     *
     * @param a recibe un objeto de tipo Instructor
     * @return Devuelve true si todo ha salido bien
     */
    public static boolean registraInstructor(Instructor a) {

        String sql = "INSERT INTO instructores ( nombre, apellidos, fecha_nacimiento, direccion, telefono, email, certificaciones, idiomas ) VALUES (?,?,?,?,?,?,?,?)";

        boolean registrado;

        //convierto la variable de la fecha para poder registrarla
        java.sql.Date sqlFecha = new java.sql.Date(a.getFechaNacimiento().getTime());

        Conexion.conectar();
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, a.getNombre());
            pst.setString(2, a.getApellidos());
            pst.setDate(3, sqlFecha);
            pst.setString(4, a.getDireccion());
            pst.setString(5, a.getTelefono());
            pst.setString(6, a.getEmail());
            pst.setString(7, a.getCertificaciones());
            pst.setString(8, a.getIdiomas());

            pst.execute();
            pst.close();

            registrado = true;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            registrado = false;

        } finally{
            Conexion.cerrarConexion();
        }

        return registrado;
    }

    /**
     * Método para cargar la lista de Instructores en los formularios que sean
     * necesarios (Crear actividad)
     *
     * @return Devuelve un Map de tipo String e Integer, con los nombres de los
     * instructores y su ID obtenidos de la Base de Datos
     */
    public static Map<String, Integer> cargaInstructores() {

        String sql = "SELECT id, nombre FROM instructores";
        ResultSet lista = null;
        Map<String, Integer> listaInstructores = new HashMap<>();

        Conexion.conectar();
        try {
            Statement consulta = conn.createStatement();
            lista = consulta.executeQuery(sql);

            //Convierto la ResultSet en un Map con los elementos, nombre e ID para enviarla a la otra clase
            while (lista.next()) {

                listaInstructores.put(lista.getString("nombre"), lista.getInt("id"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.cerrarConexion();
        }

        return listaInstructores;

    }

    /**
     * Registra una nueva salida de buceo con toda la información que queramos
     * aportar
     *
     * @param a Objeto de tipo Actividad con todas sus valores
     * @return devuelve True si se ha creado satisfactoriamente
     */
    public static boolean registrar_salidas_buceo(Actividad a) {

        String sql = "INSERT INTO salidas_buceo (fecha, hora, lugar, punto_salida, instructor_id, tipo_salida) VALUES (?,?,?,?,?,?)";

        boolean registrado;

        Conexion.conectar();
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, a.getFecha());
            pst.setString(2, a.getHora());
            pst.setString(3, a.getLugar());
            pst.setString(4, a.getPuntoSalida());
            pst.setInt(5, a.getInstructor());
            pst.setString(6, a.getTipoSalida());

            pst.execute();
            pst.close();

            registrado = true;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            registrado = false;

        }finally{
            Conexion.cerrarConexion();
        }

        return registrado;
    }

    /**
     * Realiza una consulta al a base de datos con una fecha
     *
     * @param modelo Recibe una Tabla Modelo y la utiliza para presentar la
     * información recogida en la tabla
     * @param fecha Un String con la fecha que se quiere consultar en la BD
     */
    public static void consultaRerservas(DefaultTableModel modelo, String fecha) {

        Object[] datos = new Object[7];
        String sql = "SELECT sb.id, sb.fecha, sb.hora, sb.lugar, sb.punto_salida, i.nombre AS instructor_nombre, sb.tipo_salida\n"
                + "FROM salidas_buceo sb\n"
                + "JOIN instructores i ON sb.instructor_id = i.id\n"
                + "WHERE sb.fecha = '" + fecha + "';";

        Conexion.conectar();
        try {
            ResultSet consulta = conn.createStatement().executeQuery(sql);

            while (consulta.next()) {
                datos[0] = consulta.getInt("id");
                datos[1] = consulta.getString("fecha");
                datos[2] = consulta.getString("hora");
                datos[3] = consulta.getString("lugar");
                datos[4] = consulta.getString("punto_salida");
                datos[5] = consulta.getString("instructor_nombre");
                datos[6] = consulta.getString("tipo_salida");
                

                modelo.addRow(datos);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.cerrarConexion();
        }

    }
    
    
    /**
     * Función para buscar clientes en la BD en función del nombre del cliente
     * @param model Recibe el parámetro de la tabla donde tiene que mostrar las referencias encontradas
     * @param nombre  El nombre que va a realizar la búsqueda
     */
    public static void busquedaClientes(DefaultTableModel model, String nombre){
        
        Object[] cliente = new Object[5];
        String sql="SELECT id, nombre, apellidos, numero_buceos, certificaciones\n" +
                    "FROM Clientes\n" +
                    "WHERE nombre LIKE ?";
        Conexion.conectar();
        try{
            
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, nombre);
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
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.cerrarConexion();
        }
    }
    
    
    /**
     * Función que se utiliza para registrar a un cliente en una actividad, eligiendo la botella que utilizará.
     * @param actividad La actividad a la que lo vamos a apuntar, ya sea una salida o un curso
     * @param cliente El cliente que vamos a añadir
     * @param botella La botella de aire que utiliza
     * @return Devuelve un valor tipo INT, 0 = No se a realizado la inscripción por error. 1 = El registro se ha realizado correctamente. 2= El usuario ya se encuentra en la actividad
     */
    public static int agregarClienteActividad(int actividad, int cliente, int botella) {
        int registrado = 0;

        if (!Conexion.estaClienteEnActividad(actividad, cliente)) {

            String sql = "INSERT INTO actividades (salida_id, cliente_id, botellas_id) VALUES (?,?,?)";

            Conexion.conectar();

            try {
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1, actividad);
                pst.setInt(2, cliente);
                pst.setInt(3, botella);

                pst.execute();
                pst.close();

                registrado = 1;

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                registrado = 0;

            } finally {
                Conexion.cerrarConexion();
            }

            return registrado;

        } else{
            registrado = 2;
            return registrado;
        }
        
    }
    
    public static boolean estaClienteEnActividad (int salidaId, int clienteId){
        
        String sql = "SELECT COUNT(*) FROM actividades WHERE salida_id = ? AND cliente_id = ?";
        
        Conexion.conectar();
        try{
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, salidaId);
            pst.setInt(2, clienteId);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                return rs.getInt(1)>0;
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            
        }finally{
            Conexion.cerrarConexion();
        }
        
        return false;
       
    }
    
    
    public static void cargarReservas(DefaultTableModel modelo, int id){
        String sql = "SELECT c.nombre, c.apellidos, b.capacidad, b.conexion\n" +
                    "FROM actividades a\n" +
                    "JOIN Clientes c ON a.cliente_id = c.id\n" +
                    "JOIN botellas b ON a.botellas_id = b.id\n" +
                    "WHERE a.salida_id = " + id + ";";
        Object[] cliente = new Object[4];
        
        Conexion.conectar();
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
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.cerrarConexion();
        }
        
        
    }

    /**
     * Función creada para darle el formato adecuado al a fecha
     *
     * @param date Recibe un parámetro de tipo Date
     * @return devuelve lafecha con el formato adecuado en un tipo String
     */
    public static String formatoFecha(Date date) {

        SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
        String fechaFormato = fecha.format(date);
        return fechaFormato;
    }
    
    public static Map<String, List<Object[]>> trabajoDiario(String fecha){
        
         //Creo un mapa donde la clave es el día y la hora de la actividad, y el valor es una lista de Objectos, donde se almacenan la información de los clientes
            Map<String, List<Object[]>> actividadesMap = new HashMap<>();
            
            
        
            String sql = "SELECT sb.hora, sb.tipo_salida, sb.lugar, i.nombre AS instructor_nombre, c.nombre AS cliente_nombre, c.apellidos, c.certificaciones, c.numero_buceos, b.capacidad, b.conexion "
                    + "FROM salidas_buceo sb "
                    + "JOIN instructores i ON sb.instructor_id = i.id "
                    + "JOIN actividades a ON sb.id = a.salida_id "
                    + "JOIN clientes c ON a.cliente_id=c.id "
                    + "JOIN botellas b ON a.botellas_id=b.id "
                    + "WHERE sb.fecha=?";
            Conexion.conectar();
            try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, fecha);
            ResultSet resultado = pst.executeQuery();
            
            
           
            
            while(resultado.next()){
                String clave = resultado.getString("hora") + "-" + resultado.getString("tipo_salida") + "-" + resultado.getString("lugar") + "-" + resultado.getString("instructor_nombre");
                
                Object[] row = {
                    
                    resultado.getString("cliente_nombre"),
                    resultado.getString("apellidos"),
                    resultado.getString("certificaciones"),
                    resultado.getString("numero_buceos"),
                    resultado.getString("capacidad"),
                    resultado.getString("conexion")
                };
                
                actividadesMap.computeIfAbsent(clave, k-> new ArrayList<>()).add(row);
                
        
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
                Conexion.cerrarConexion();
            }
        
        return actividadesMap;
        
    }
}
