package com.example.SubApp.controllers;

import com.example.SubApp.models.InstructorModel;
import com.example.SubApp.services.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/instructores")
public class InstructorController {


    @Autowired
    private InstructorService instructorService;


    @GetMapping(path = "/{id}")
    public Optional<InstructorModel> getInstructorById(@PathVariable int id){
        return this.instructorService.getById(id);
    }


    @PostMapping("/registroInstructores")
    public InstructorModel saveInstructor(@RequestBody InstructorModel instructor){

        return this.instructorService.saveInstructor(instructor);
    }

    @GetMapping("/cargaInstructores")
    Map<String, Integer> cargaInstructores(){

        return this.instructorService.cargaInstructores();

    }
}
