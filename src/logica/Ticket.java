/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author lgutierrez
 */
public class Ticket {
    private static int count=0;
    int id;
    String funcion;
    String tipo;
    String asiento;
    int edad;
    int valor;
    int descuento;
    double monto;

    public Ticket(String funcion, String tipo, String asiento, int edad, int valor, int descuento) {
        this.id = ++count;
        this.funcion = funcion;
        this.tipo = tipo;
        this.asiento = asiento;
        this.edad = edad;
        this.valor = valor;
        this.descuento = descuento;
        this.monto = valor-valor*descuento/100;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getAsiento() {
        return asiento;
    }

    public int getValor() {
        return valor;
    }
    public int getEdad() {
        return edad;
    }

    public int getDescuento() {
        return descuento;
    }
    public double getMonto() {
        return monto;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }
    
    
    
}
