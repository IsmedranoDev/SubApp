package com.example.SubApp.repositories;

import com.example.SubApp.models.InstructorModel;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInstructorRepository extends JpaRepository<InstructorModel, Integer> {


}
