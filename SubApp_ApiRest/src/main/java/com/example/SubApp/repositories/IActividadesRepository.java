package com.example.SubApp.repositories;

import com.example.SubApp.models.ActividadesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface IActividadesRepository extends JpaRepository<ActividadesModel, Integer> {

    //Método para buscar la actividad por id
    ArrayList<ActividadesModel> findByNumeroSalida(int numeroSalida);
}
