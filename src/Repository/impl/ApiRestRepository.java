/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.impl;

import Clases.Objetos.Actividad;
import Clases.Objetos.ClaseReservas;
import Clases.Objetos.Cliente;
import Clases.Objetos.Instructor;
import Repository.Repository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author israelmedrano
 */
public class ApiRestRepository<T> implements Repository<T> {

    @Override
    public List<T> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public T findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void save(T entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * 
     * @param requestMethod Indica el modo de realizar la petición GET, POST
     * @param direccion Marca la dirección de la petición a la API
     * @return devuelve un objeto tipo HttpURLConnection
     * @throws IOException 
     */
    public HttpURLConnection abrirConexion(String requestMethod, String direccion) throws IOException {

        URL url = new URL("http://localhost:4000/" + direccion);
        conexion = (HttpURLConnection) url.openConnection();
        conexion.setRequestMethod(requestMethod);
        conexion.setRequestProperty("Content-Type", "application/json; utf-8");
        conexion.setRequestProperty("Accept", "application/json");
        conexion.setDoOutput(true);
        return conexion;

    }

    /**
     * Cierra cualquier conexion abierta
     * @param conexion La conexion que deseamos cerrar
     */
    public void cerrarConexion(HttpURLConnection conexion) {

        if (conexion != null) {
            conexion.disconnect();
        }

    }

    /**
     * Registra un cliente en la base de datos
     * @param a objeto de tipo cliente
     * @return devuelve True si todo ha salido bien
     */
    public boolean registraCliente(Cliente a) {

        try {
            //Establezco la conexión

            HttpURLConnection conn = abrirConexion("POST", "clientes/registroCliente");

            //Convierto el cliente a tipo json
            ObjectMapper clienteMapeado = new ObjectMapper();
            String json = clienteMapeado.writeValueAsString(a);

            //Envío el json
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = json.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            //Leo la respuesta
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {

                StringBuilder respuesta = new StringBuilder();
                String respuestaLinea;
                while ((respuestaLinea = br.readLine()) != null) {
                    respuesta.append(respuestaLinea.trim());
                }
                System.out.println(respuesta.toString());

            }
            return true;

        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

/**
 * Carga todos los clientes que existen en la base de datos
 * @param modelo Objeto DefaultTableModel donde se cargar'an los resultados
 */    
    public void cargarClientes(DefaultTableModel modelo) {
      
        try {

            HttpURLConnection conn = abrirConexion("GET", "clientes");
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String output;

            output = br.readLine();

            ObjectMapper objectMapper = new ObjectMapper();
            Cliente[] clientes = objectMapper.readValue(output, Cliente[].class);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            objectMapper.setDateFormat(dateFormat);

            for (Cliente cliente : clientes) {
                Object[] tablaClientes = {
                    cliente.getID(),
                    cliente.getNombre(),
                    cliente.getApellidos(),
                    cliente.getNumeroBuceos(),
                    cliente.getCertificaciones()
                };
                modelo.addRow(tablaClientes);
               
            }

        } catch (Exception e) {
            e.printStackTrace();
            
        }

    }
    
    /**
     * Busca clientes especificos tomando como referencia el nombre del cliente
     * @param modelo el objeto de tipo DefaultTAbleModel donde se cargar'an los datos
     * @param nombre El nombre del cliente que deseamos buscar
     */
    public void busquedaClientes(DefaultTableModel modelo, String nombre){
        
        try {
            HttpURLConnection conn = abrirConexion("GET", "clientes/nombre/" + nombre);
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            
            String output = br.readLine();
            
            ObjectMapper objectMapper = new ObjectMapper();
            Cliente[] clientes = objectMapper.readValue(output, Cliente[].class);
            
            for(Cliente cliente : clientes){
                Object[] tablaClientes={
                    cliente.getID(),
                    cliente.getNombre(),
                    cliente.getApellidos(),
                    cliente.getNumeroBuceos(),
                    cliente.getCertificaciones()
                };
                modelo.addRow(tablaClientes);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ApiRestRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     * Consulta las actividades que hay creadas para un d'ia espec'ifico
     * @param modelo La tabla donde se cargarán los datos
     * @param fecha Objeto de tipo Date para realizar la búsqueda
     */
    public void consultaReservas(DefaultTableModel modelo, Date fecha) {

        try {
            // Object[] datos = new Object[7];
            
            String fechaStr = formatoFecha(fecha);
            HttpURLConnection conn = abrirConexion("GET", "salidas_buceo/fecha/" + fechaStr);
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String output;

            output = br.readLine();
          
            ObjectMapper objectMapper = new ObjectMapper();
            Actividad[] actividades = objectMapper.readValue(output, Actividad[].class);
            //objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd");
            objectMapper.setDateFormat(dateFormat);
            
            for(Actividad actividad : actividades){
                Object[] tablaActividades = {
                    actividad.getId(),
                    actividad.getFecha(),
                    actividad.getHora(),
                    actividad.getLugar(),
                    actividad.getPuntoSalida(),
                    actividad.getInstructorNombre(),
                    actividad.getTipoSalida()
                };
                modelo.addRow(tablaActividades);
            }

        } catch (IOException ex) {
            Logger.getLogger(ApiRestRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    /**
     * Comprueba los clientes que ya han realizado la reserva para una actividad
     * @param modelo El objeto tipo DefaultTableModel donde queremos que se cargue la información
     * @param reserva_id el número de reserva que queremos buscar, tipo int
     */
    public void cargarReservas(DefaultTableModel modelo, int reserva_id){
        try{
            
            HttpURLConnection conn = abrirConexion("GET", "actividades/" + reserva_id);
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            
            
            String output = br.readLine();
            
            ObjectMapper objectMapper = new ObjectMapper();
            ClaseReservas[] reservas = objectMapper.readValue(output, ClaseReservas[].class);
            
            for(ClaseReservas reserva : reservas){
                Object[] tablaReservas= {
                    reserva.getNombre(),
                    reserva.getApellidos(),
                    reserva.getNumeroBuceos(),
                    reserva.getCapacidadBotella(),
                    reserva.getConexionBotella()
                
                };
                modelo.addRow(tablaReservas);
                
            }
            
        } catch(IOException ex){
            Logger.getLogger(ApiRestRepository.class.getName()).log(Level.SEVERE, null, ex);

        }
    }      
     
    /**
     * Registra a un cliente en una actdividad concreta realizando la reserva
     * @param actividad id de la actividad donde se desea registrar
     * @param cliente id del cliente que se va a registrar
     * @param botella id de la botella que va a usar el cliente, la lógica se encuentra en la propia interfaz
     * @return devuelve: 0 si algo ha ido mal; 1 si el registro se ha efectuado correctamente; 2 si el cliente ya está registrado en esa actividad
     */
    public int registraReserva(int actividad, int cliente, int botella){
        
        int codigoRespuesta=0;
        try{
            
            
            HttpURLConnection conn = abrirConexion("POST", "actividades/addclienteactividad");
            
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setDoOutput(true); //Para enviar el body
            
            String urlParametros = "actividad=" + actividad + "&cliente=" + cliente + "&botella=" + botella;
            
            OutputStream os = conn.getOutputStream();
            os.write(urlParametros.getBytes("UTF-8"));
            os.flush();
            os.close();
            
            
            //Verificar el código de respuesta
            codigoRespuesta = conn.getResponseCode();
            System.out.println("Codigo respuesta:" + codigoRespuesta);
            
            //Verificamos si el cliente esta registrado
            if (codigoRespuesta == HttpURLConnection.HTTP_CONFLICT){
                return 2;
            }
          
            
        }catch(IOException ex){
            ex.printStackTrace();
            
        }
          if(codigoRespuesta == HttpURLConnection.HTTP_OK){
                return 1;
            }else {
                return 0;
            }
          
          
    }
    
    /**
     * Registra a una nueva instructora en la base de datos
     * @param instructor Objeto de tipo Instructor para registrar
     * @return Devuelve True si se ha realizado el registro
     */
       public boolean registraInstructor(Instructor instructor) {
        try{
            ConexionAPI conexion = new ConexionAPI();
            HttpURLConnection conn = conexion.abrirConexion("POST", "instructores/registroInstructores");
            
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            
            ObjectMapper instructorMapeado = new ObjectMapper();
            String json = instructorMapeado.writeValueAsString(instructor);
            
            try(OutputStream os = conn.getOutputStream()){
                byte[] input = json.getBytes("utf-8");
                os.write(input, 0, input.length);
                
            }
            
            int codigoRespuesta = conn.getResponseCode();
            if(codigoRespuesta != HttpURLConnection.HTTP_OK){
                System.out.println("Error en la solicitud: " + codigoRespuesta);
                return false;
            }
            
        try(BufferedReader br = new BufferedReader ( new InputStreamReader(conn.getInputStream(), "utf-8"))){
            
            StringBuilder respuesta = new StringBuilder();
            String respuestaLinea;
            while((respuestaLinea = br.readLine()) !=null){
                respuesta.append(respuestaLinea.trim());
            }
            System.out.println(respuesta.toString());
            
                
            }
        conn.disconnect();
            return true;
            
        }catch(Exception ex){
             ex.printStackTrace();
             return false;
        }
           
    }  
       
       /**
        * Carga una lista de instructores junto con su Id
        * @return Devuelve un objeto tipo Map con el nombre y el id de los instructores
        */
    public Map<String, Integer> cargaInstructores() {
        
        Map<String, Integer> mapaInstructores = new HashMap<>();
        
        try{
            
            //Establezco la conexión
            ConexionAPI conexion = new ConexionAPI();
            HttpURLConnection conn = conexion.abrirConexion("GET", "instructores/cargaInstructores");
          
            
            //Leo la respuesta del servidor y la almaceno en un String
            BufferedReader in = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            
            String inputLine;
            inputLine= in.readLine();
            in.close();
            System.out.println(inputLine);
            
            //Convierto el Sttring a un MAP
            Gson mapa = new Gson();
            Type type = new TypeToken<Map<String, Integer>>(){}.getType();
            mapaInstructores = mapa.fromJson(inputLine.toString(), type);
            
            return mapaInstructores;
            
            
            
            
            
            
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
            
        }
        
        
    }       
       
       
    
    /**
     * Registra una actividad en la base de datos
     * @param actividad Objeto de tipo Actividad
     * @return Devuelve True si se ha realizado el registro
     */
    public boolean registrar_salida_buceo(Actividad actividad){
        
        
        try{
            //conexion
            HttpURLConnection conn = abrirConexion("POST", "salidas_buceo/registraSalida");
            
            //Convierto el objeto Actividad a tipo json
            ObjectMapper salidaMapeada = new ObjectMapper();
            String json = salidaMapeada.writeValueAsString(actividad);
            
            //envio el json
            try (OutputStream os = conn.getOutputStream()){
                byte[] input = json.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            
            System.out.println("Enviando JSON: " + json);

            
             try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {

                StringBuilder respuesta = new StringBuilder();
                String respuestaLinea;
                while ((respuestaLinea = br.readLine()) != null) {
                    respuesta.append(respuestaLinea.trim());
                }
                System.out.println(respuesta.toString());

            }
           return true;
        } catch (IOException ex){
            ex.printStackTrace();
            return false;
        }
        
    }
    
    
    /**
     * Crea un Map con las actividades que hay en un día concreto
     * @param fecha Variable de tipo String con la fecha que se quiere consultar yyyy-MM-dd
     * @return Devuelve un Map con la información de la salida, y una List con un Objeto con información sobre los clientes que va a acudir a esa actividad
     */
public  Map<String, List<Object[]>> trabajoDiario(String fecha) {

    Map<String, List<Object[]>> actividadesMap = new HashMap<>();

    try {
        // Formatear la fecha como String para la URL
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        //String fechaStr = formatoFecha.format(fecha);

        // Configurar y abrir la conexión
        HttpURLConnection conn = abrirConexion("GET", "actividades/trabajoDiario?fecha=" + fecha);
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        
        // Leer la respuesta JSON
        String output = br.readLine();

        // Configuración del ObjectMapper para convertir el JSON
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(formatoFecha);
        
        // Deserializar JSON en Map<String, List<Object[]>>
        TypeReference<Map<String, List<Object[]>>> typeReferencia = new TypeReference<>() {};
        actividadesMap = objectMapper.readValue(output, typeReferencia);

    } catch (IOException ex) {
        Logger.getLogger(ApiRestRepository.class.getName()).log(Level.SEVERE, null, ex);
    }

    return actividadesMap; // Devolvemos el mapa aquí y finalizamos el método
}

public boolean acceder(String user, String pass){
     String SSQL="SELECT Nombre, Contrasenya FROM Usuarios WHERE Nombre =? AND Contrasenya =?";
        
         String url = "jdbc:mysql://localhost:3306/SubApp";
         Connection conn;
        String userMysql = "root";
        String passMysql = "";
        Connection connMysql;
        PreparedStatement pst;
        ResultSet rs;
        
        try{
            conn = DriverManager.getConnection(url,userMysql,passMysql);
            pst = conn.prepareStatement(SSQL);
            pst.setString(1, user);
            pst.setString(2,pass);
            rs = pst.executeQuery();
            
            return rs.next();
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return false;  
    
}
  

    /**
     * Devuelve un formato de fecha específico
     * @param date Variable de tipo Date
     * @return Devuelve la fecha en formato String yyyy-MM-dd
     */
    public String formatoFecha(Date date){
        SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
        
        String fechaStr = fecha.format(date);
        return fechaStr;
    }

    private HttpURLConnection conexion;

}
