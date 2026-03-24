package com.intercambios.intercambios_api.model;

public class RequerimientosEnergeticos {
    //private double caloriasDiarias;
    private double peso;
    private double altura;
    private int edad;
    private String sexo;
    private double factorActividad;
    public RequerimientosEnergeticos(double peso,double altura,int  edad,String sexo,double factorActividad){
        //this.caloriasDiarias=caloriasDiarias;
        this.peso=peso;
        this.altura=altura;
        this.edad=edad;
        this.sexo=sexo;
        this.factorActividad=factorActividad;
    }
    //creacion de getter para conseguir los datos
    public double getPeso() {
        return peso;
    }

    public double getAltura() {
        return altura;
    }

    public int getEdad() {
        return edad;
    }

    public String getSexo() {
        return sexo;
    }

    public double getFactorActividad() {
        return factorActividad;
    }
    //setter para colocar los datos
    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setFactorActividad(double factorActividad) {
        this.factorActividad = factorActividad;
    }
}
