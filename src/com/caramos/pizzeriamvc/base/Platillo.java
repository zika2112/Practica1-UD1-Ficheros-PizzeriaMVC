package com.caramos.pizzeriamvc.base;

import java.time.LocalDate;

public class Platillo {

    private String nombre;
    private String ingredientes;
    private String salsaBase;
    private String tamaño;
    private double precio;
    private LocalDate fechaDelPedido;

    public Platillo(){

    }

    public Platillo(String nombre, String ingredientes, String salsaBase, String tamaño, double precio, LocalDate fechaDelPedido) {
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.salsaBase = salsaBase;
        this.tamaño = tamaño;
        this.precio = precio;
        this.fechaDelPedido = fechaDelPedido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getSalsaBase() {
        return salsaBase;
    }

    public void setSalsaBase(String salsaBase) {
        this.salsaBase = salsaBase;
    }

    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public LocalDate getFechaDelPedido() {
        return fechaDelPedido;
    }

    public void setFechaDelPedido(LocalDate fechaDelPedido) {
        this.fechaDelPedido = fechaDelPedido;
    }
}
