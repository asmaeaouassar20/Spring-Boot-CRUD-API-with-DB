package com.example.CRUD_API.controller;

import com.example.CRUD_API.entity.Patient;
import com.example.CRUD_API.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/patient")
public class PatientController {
    private final PatientService patientService;
    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public List<Patient> getAllPatients(){
        return patientService.getAllPatients();
    }

    @PostMapping
    public void addNewPatient(@RequestBody Patient newPatient){
        patientService.addNewPatient(newPatient);
    }

    @DeleteMapping(path="{patientId}")
    public void deletePatientById(@PathVariable("patientId") Long id){
        patientService.deletePatientById(id);
    }

    @PutMapping(path="/{patientId}")
    public void updatePatientById(
            @PathVariable("patientId") Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email
    ){
        patientService.updatePatientById(id,name,email);
    }
}
