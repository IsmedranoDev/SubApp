package com.example.SubApp.controllers;

import com.example.SubApp.models.ReservaActividad;
import com.example.SubApp.services.ActividadesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/actividades")
public class Actividadescontroller {

    @Autowired
    private ActividadesService actividadesService;


    @GetMapping(path = "/{id}")
    public ArrayList<ReservaActividad> getActividadesById(@PathVariable("id") int id){
        return this.actividadesService.getActividadesByNumeroSalida(id);


    }

    @PostMapping("/addclienteactividad")
    public ResponseEntity<String> addClienteActividad (
            @RequestParam("actividad") int actividad,
            @RequestParam ("cliente") int cliente,
            @RequestParam ("botella") int botella)
    {
        //Verificamos si el cliente ya esta registrado en la actividad
        if(actividadesService.estaRegistradoEnActividad(actividad, cliente)){
            return ResponseEntity.status(HttpURLConnection.HTTP_CONFLICT).body("El cliente ya está registrado en la actividad");
        }

        //Si el cliente no esta registrado, procedemos a la inserccion en la base de datos
    int result = actividadesService.addClienteActividad(actividad, cliente, botella);

    if(result==1){
        return ResponseEntity.ok("Reserva realizada con éxito");
    } else {
        return ResponseEntity.status(500).body("Error al realizar la reserva");
    }

    }

    @GetMapping ("/trabajoDiario")
    public ResponseEntity<Map<String, List<Object[]>>> obtenerTrabajoDiario(@RequestParam("fecha") String fecha) {
        Map<String, List<Object[]>> resultado = actividadesService.trabajoDiario(fecha);
        return ResponseEntity.ok(resultado);
    }


}


