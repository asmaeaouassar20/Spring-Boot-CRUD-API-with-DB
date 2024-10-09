package com.example.CRUD_API.repository;

import com.example.CRUD_API.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {


    @Query("SELECT p FROM Patient p WHERE p.email=?1") // annotation de Spring Data JPA
                                                      // utilisée pour écrire une requete SQL personnalisée
                                                      // en utilisant le JPQL
                                                     //JPQL: Java Persistence Query Language
    Optional<Patient> findPatientByEmail(String email);
}
