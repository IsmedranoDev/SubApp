package com.example.SubApp.controllers;


import com.example.SubApp.models.SalidasBuceoModel;
import com.example.SubApp.services.SalidasBuceoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/salidas_buceo")
public class SalidasBuceoController {

    @Autowired
    private SalidasBuceoService salidasBuceoService;


    @GetMapping
    public ArrayList<SalidasBuceoModel> getSalidasBuceo(){

        return this.salidasBuceoService.getSalidasBuceo();
    }


    @GetMapping(path = ("/fecha/{fecha}"))
    public ArrayList<SalidasBuceoModel> getSalidaByFecha(@PathVariable("fecha") String fecha){
        return this.salidasBuceoService.getSalidasBuceoByFecha(fecha);
    }

    @PostMapping(path =  ("/registraSalida"))
    public SalidasBuceoModel saveSalida(@RequestBody SalidasBuceoModel salidaBuceo){

        return this.salidasBuceoService.saveSalida(salidaBuceo);
    }
}


