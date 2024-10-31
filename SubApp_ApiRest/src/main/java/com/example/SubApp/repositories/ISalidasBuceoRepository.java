package com.example.SubApp.repositories;

import com.example.SubApp.models.SalidasBuceoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface ISalidasBuceoRepository extends JpaRepository<SalidasBuceoModel, Integer> {

    //Método para buscar salidas por fecha
    ArrayList<SalidasBuceoModel> findByFecha(Date fecha);
}


