package com.example.SubApp.services;

import com.example.SubApp.models.*;
import com.example.SubApp.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ActividadesService {

    @Autowired
    IActividadesRepository actividadesRepository;

    @Autowired
    IInstructorRepository instructorRepository;
    @Autowired
    IClienteRepository clienteRepository;

    @Autowired
    ISalidasBuceoRepository salidasBuceoRepository;
    @Autowired
    IBotellaRepository botellaRepository;
     @Autowired
     private JdbcTemplate jdbcTemplate;


    public ArrayList<ReservaActividad> getActividadesByNumeroSalida(int numeroSalida){

        ArrayList<ActividadesModel> actividades = actividadesRepository.findByNumeroSalida(numeroSalida);
        ArrayList<ReservaActividad> actividadesInfo = new ArrayList<>();

       for(ActividadesModel actividad : actividades){
           ClienteModel cliente = clienteRepository.findById(actividad.getCliente_id()).orElse(new ClienteModel());
           BotellaModel botella = botellaRepository.findById(actividad.getBotellas_id()).orElse(new BotellaModel());

           ReservaActividad info = new ReservaActividad();
           info.setNombre(cliente.getNombre());
           info.setApellidos(cliente.getApellidos());
           info.setNumeroBuceos(cliente.getNumero_buceos());
           info.setCapacidadBotella(botella.getCapacidad());
           info.setConexionBotella(botella.getConexion());

           actividadesInfo.add(info);
       }

        return actividadesInfo;
    }

    public int addClienteActividad (int actividad, int cliente, int botella){

        String sql = "INSERT INTO actividades (salida_id, cliente_id, botellas_id) VALUES (?,?,?)";
        return jdbcTemplate.update(sql, actividad, cliente, botella);
    }

    public boolean estaRegistradoEnActividad(int actividad, int cliente){
        String sql = "SELECT COUNT(*) FROM actividades WHERE salida_id = ? AND cliente_id = ?";
        int count = jdbcTemplate.queryForObject(sql, new Object[]{actividad, cliente}, Integer.class);
        return count > 0;
    }
    public Map<String, List<Object[]>> trabajoDiario(String fecha){
        String sql = "SELECT sb.hora, sb.tipo_salida, sb.lugar, i.nombre AS instructor_nombre, c.nombre AS cliente_nombre, c.apellidos, c.certificaciones, c.numero_buceos, b.capacidad, b.conexion "
                + "FROM salidas_buceo sb "
                + "JOIN instructores i ON sb.instructor_id = i.id "
                + "JOIN actividades a ON sb.id = a.salida_id "
                + "JOIN clientes c ON a.cliente_id=c.id "
                + "JOIN botellas b ON a.botellas_id=b.id "
                + "WHERE sb.fecha=?";

        List<Map<String, Object>> trabajoDiario = jdbcTemplate.queryForList(sql, fecha);
        Map<String, List<Object[]>> resultado = new HashMap<>();

        for(Map<String, Object> trabajosDiarios : trabajoDiario){
            String clave = trabajosDiarios.get("hora") + "-" + trabajosDiarios.get("tipo_salida") + "-" + trabajosDiarios.get("lugar") + "-" + trabajosDiarios.get("instructor_nombre");

            Object[] valores = {
                    trabajosDiarios.get("cliente_nombre"),
                    trabajosDiarios.get("apellidos"),
                    trabajosDiarios.get("certificaciones"),
                    trabajosDiarios.get("numero_buceos"),
                    trabajosDiarios.get("capacidad"),
                    trabajosDiarios.get("conexion")
            };

            resultado.computeIfAbsent(clave, k -> new ArrayList<>()).add(valores);
        }

        return resultado;
    }



}
