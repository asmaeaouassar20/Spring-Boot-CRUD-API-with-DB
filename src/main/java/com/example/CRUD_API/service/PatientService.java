package com.example.CRUD_API.service;

import com.example.CRUD_API.entity.Patient;
import com.example.CRUD_API.repository.PatientRepository;
import jakarta.persistence.Transient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class PatientService {
    private final PatientRepository patientRepository;
    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getAllPatients(){
        return patientRepository.findAll();
    }

    public void addNewPatient(Patient newPatient) {
        Optional<Patient> optionalPatientFromDB=patientRepository.findPatientByEmail(newPatient.getEmail());
        if(optionalPatientFromDB.isPresent()){
            throw new IllegalStateException("email taken");
        }
        patientRepository.save(newPatient);
    }

    public void deletePatientById(Long id) {
        boolean exist=patientRepository.existsById(id);
        if(!exist){
            throw new IllegalStateException("patient with id "+id+" does not exist in DB");
        }
        patientRepository.deleteById(id);
    }

    @Transactional
    public void updatePatientById(Long id, String name, String email) {
        Patient patient=patientRepository.findById(id)
                .orElseThrow(()->new IllegalStateException("patient with id "+id+" does not exist"));

        if(name!=null && name.length()>0 && !Objects.equals(patient.getName(),name)){
            patient.setName(name);
        }
        if(email!=null && email.length()>0 && !Objects.equals(patient.getEmail(),email)){
            Optional<Patient> optionalPatientFromDB=patientRepository.findPatientByEmail(email);
            if(optionalPatientFromDB.isPresent()){
                throw new IllegalStateException("taken email");
            }
            patient.setEmail(email);
        }
    }

    /*
    ------   L'annotation @Transactional   ---------------
    - cette annotation est dans Spring Boot utilisée pour indiquer que cette méthode doit etere exécutée dans le cadre d'une transaction
    - Une transaction garantit que plusieurs opérations de lecture ou d'écriture dans la BD sont traitées de maniere atomique
    - càd que toutes les opérations réussissent ou échouent ensemble
    ==> Si le nom est mis à jour avec succes mais qu'une erreur survient lors de
    la vérification de l'émail(email déjà pris)
    alors aucune des modifications (ni du nom, ni de l'email) ne sera enregistrée dans la BD grace à la TRANSACTION
     */
}
