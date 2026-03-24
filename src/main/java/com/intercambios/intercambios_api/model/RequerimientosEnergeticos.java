package com.intercambios.intercambios_api.model;

public class RequerimientosEnergeticos {
    //private double caloriasDiarias;
    private double peso;
    private double altura;
    private int edad;
    private String sexo;
    private double factorActividad;
    public RequerimientosEnergeticos(double peso,double altura,int  edad,String sexo,double factorActividad){
        this.caloriasDiarias=caloriasDiarias;
    }

    public double getCaloriasDiarias() {
        return caloriasDiarias;
    }

    public void setCaloriasDiarias(double caloriasDiarias) {
        this.caloriasDiarias = caloriasDiarias;
    }
}
