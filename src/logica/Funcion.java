/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.time.LocalDate;
import java.util.HashMap;
/**
 *
 * @author lgutierrez
 */
public class Funcion {
    String nombreFuncion;
    LocalDate fechaFuncion;
    int[] capacidad;
    int[] precio;

    public Funcion(String nombreFuncion, LocalDate fechaFuncion, int[] capacidad, int[] precio) {
        this.nombreFuncion = nombreFuncion;
        this.fechaFuncion = fechaFuncion;
        this.capacidad = capacidad;
        this.precio = precio;
    }

    public String getNombreFuncion() {
        return nombreFuncion;
    }

    public void setNombreFuncion(String nombreFuncion) {
        this.nombreFuncion = nombreFuncion;
    }

    public LocalDate getFechaFuncion() {
        return fechaFuncion;
    }

    public void setFechaFuncion(LocalDate fechaFuncion) {
        this.fechaFuncion = fechaFuncion;
    }

    public int[] getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int[] capacidad) {
        this.capacidad = capacidad;
    }

    public int[] getPrecio() {
        return precio;
    }

    public void setPrecio(int[] precio) {
        this.precio = precio;
    }
    
    
    
}
