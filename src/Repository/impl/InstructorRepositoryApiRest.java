/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.impl;

import Clases.Objetos.Instructor;
import Repository.InstructorRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author israelmedrano
 */
public class InstructorRepositoryApiRest extends InstructorRepository{
    
    

    @Override
    public boolean registraInstructor(Instructor instructor) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
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

    }

