package com.intercambios.intercambios_api.model;
import jakarta.persistence.*;

    @Entity
    @Table(name = "grupo_alimento")
    public class GrupoAlimento {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column(nullable = false, length = 100)
        private String nombre;

        private String descripcion;

        public GrupoAlimento() {}

        public Integer getId() { return id; }
        public void setId(Integer id) { this.id = id; }

        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }

        public String getDescripcion() { return descripcion; }
        public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    }

