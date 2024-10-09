package com.example.CRUD_API.configuration;

import com.example.CRUD_API.entity.Patient;
import com.example.CRUD_API.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class PatientConfiguration {
    @Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository){
       return args->{
           Patient asmae=new Patient(
                   "asmae",
                   "asm@gmail.com",
                   LocalDate.of(2003,5,3)
           );
           Patient ali=new Patient(
                   "ali",
                   "ali@gmail.com",
                   LocalDate.of(2007,7,6)
           );
          // patientRepository.saveAll(List.of(asmae,ali));
       };
    }
}
