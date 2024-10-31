package com.example.SubApp.services;

import com.example.SubApp.models.InstructorModel;
import com.example.SubApp.models.SalidasBuceoModel;
import com.example.SubApp.repositories.IInstructorRepository;
import com.example.SubApp.repositories.ISalidasBuceoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;

@Service
public class SalidasBuceoService {

    @Autowired
    ISalidasBuceoRepository salidasBuceo;

    @Autowired
    IInstructorRepository instructorRepository;



    public ArrayList<SalidasBuceoModel> getSalidasBuceo(){
        ArrayList<SalidasBuceoModel> salidas = (ArrayList<SalidasBuceoModel>) salidasBuceo.findAll();


        for(SalidasBuceoModel salida : salidas){
            InstructorModel instructor = instructorRepository.findById(salida.getInstructor_id()).orElse(new InstructorModel());
            salida.setInstructorNombre(instructor.getNombre());
        }


       return salidas;
    }


    public ArrayList<SalidasBuceoModel> getSalidasBuceoByFecha(String fecha){

        Date fechaSql = Date.valueOf(fecha);
        ArrayList<SalidasBuceoModel> salidas = (ArrayList<SalidasBuceoModel>) salidasBuceo.findByFecha(fechaSql);

        for(SalidasBuceoModel salida : salidas){
            InstructorModel instructor = instructorRepository.findById(salida.getInstructor_id()).orElse(new InstructorModel());
            salida.setInstructorNombre(instructor.getNombre());
        }


        return salidas;

    }

    public SalidasBuceoModel saveSalida(SalidasBuceoModel salidaBuceo) {

        return salidasBuceo.save((salidaBuceo));
    }
}
