package com.example.SubApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.SubApp.models.ClienteModel;
@Repository
public interface IClienteRepository extends JpaRepository<ClienteModel, Integer> {
}
