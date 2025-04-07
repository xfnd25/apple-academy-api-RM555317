package br.senac.sp.appleacademyapi.cohort;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Cohort {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    
}
