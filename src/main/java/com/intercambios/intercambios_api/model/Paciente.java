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

    @Column(nullable = false)
    private  Double peso;

    @Column(nullable = false)
    private  Double altura;

    @Column(name= "factor_actividad", nullable = false)
    private Double factorActividad;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private  Sexo sexo;// como sexo puede variar llamamos la clase sexo donde estaran
    //almacenados las formulas de harrys-benedict para hombre y mujer, y para menores de 2 años
    // otras formulas



}
