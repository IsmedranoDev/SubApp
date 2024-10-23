/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.impl;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author israelmedrano
 */
public class ConexionAPI {
    
   private  HttpURLConnection conexion;
   
    
   /**
    * Método para abrir la conexión
    */
    public HttpURLConnection abrirConexion(String requestMethod, String direccion) throws IOException{
       
           URL url = new URL("http://localhost:4000/" + direccion);
           conexion = (HttpURLConnection) url.openConnection();
           conexion.setRequestMethod(requestMethod);
           conexion.setRequestProperty("Content-Type", "application/json; utf-8");
           conexion.setRequestProperty("Accept", "application/json");
           conexion.setDoOutput(true);
           return conexion;
           
   }

   

    
    public void cerrarConexion(HttpURLConnection conexion) {
        
        if(conexion!=null){
            conexion.disconnect();
        }
        
        
    }
    
}
