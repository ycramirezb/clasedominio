package com.Ejercicio.Ejercicio.domain;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Individuo")

public class Individuo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_individuo")
    private Integer idIndividuo;
    private String nombre;
    private String apellido;
    private int edad;
    private String email;
    private String telefono;
    
}
