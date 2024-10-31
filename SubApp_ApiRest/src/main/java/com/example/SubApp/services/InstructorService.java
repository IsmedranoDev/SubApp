package com.example.SubApp.services;

import com.example.SubApp.models.InstructorModel;
import com.example.SubApp.repositories.IInstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class InstructorService {

    @Autowired
    IInstructorRepository instructorRepository;

    public ArrayList<InstructorModel> getInstructores(){

        return(ArrayList<InstructorModel>) instructorRepository.findAll();
    }


    public Optional<InstructorModel> getById(int id) {
        return instructorRepository.findById(id);
    }

    public InstructorModel saveInstructor(InstructorModel instructor){

        return instructorRepository.save(instructor);
    }


    public Map<String, Integer> cargaInstructores(){

        ArrayList<InstructorModel> listaInstructores = (ArrayList<InstructorModel>) instructorRepository.findAll();

        Map<String, Integer> mapaInstructores = new HashMap<>();

        //Itero la lista para insertarla en el Map
        for(InstructorModel instructor : listaInstructores){
            mapaInstructores.put(instructor.getNombre(), instructor.getId());

        }
        return mapaInstructores;
    }

    public boolean deleteInstructor(int id){

        try {
            instructorRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }


}
