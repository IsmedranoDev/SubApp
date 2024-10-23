/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.impl;

import Clases.Objetos.Actividad;
import Clases.Objetos.ClaseReservas;
import Clases.Objetos.Cliente;
import Interfaces.Reservas;
import Repository.Repository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

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

    public HttpURLConnection abrirConexion(String requestMethod, String direccion) throws IOException {

        URL url = new URL("http://localhost:4000/" + direccion);
        conexion = (HttpURLConnection) url.openConnection();
        conexion.setRequestMethod(requestMethod);
        conexion.setRequestProperty("Content-Type", "application/json; utf-8");
        conexion.setRequestProperty("Accept", "application/json");
        conexion.setDoOutput(true);
        return conexion;

    }

    public void cerrarConexion(HttpURLConnection conexion) {

        if (conexion != null) {
            conexion.disconnect();
        }

    }

    public boolean registraCliente(Cliente a) {

        try {
            //Establezco la conexión

            HttpURLConnection conn = abrirConexion("POST", "clientes");

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
            Logger.getLogger(ClienteRepositoryApiRest.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

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
    
    public void busquedaClientes(DefaultTableModel modeo, String nombre){
        
    }

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
            Logger.getLogger(ClienteRepositoryApiRest.class.getName()).log(Level.SEVERE, null, ex);
            
        }
          if(codigoRespuesta == HttpURLConnection.HTTP_OK){
                return 1;
            }else {
                return 0;
            }
          
          
    }
    
    
    public String formatoFecha(Date date){
        SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
        
        String fechaStr = fecha.format(date);
        return fechaStr;
    }

    private HttpURLConnection conexion;

}
