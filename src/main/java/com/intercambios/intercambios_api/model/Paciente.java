package com.intercambios.intercambios_api.model;
import jakarta.persistence.*;
@Entity
@Table(name = "paciente")

public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //validamos la columna y establecemos que no puede estar vacia
    @Column(nullable = false, length=200)
    private String nombre;

    @Column(nullable = false)
    private  Integer edad;



}
