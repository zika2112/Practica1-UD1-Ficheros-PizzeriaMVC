package com.caramos.pizzeriamvc.base;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Platillo {

    public static final List<String> SALSAS_DISPONIBLES = Arrays.asList(
            "margherita",
            "pesto",
            "biancca",
            "4 formaggi"
    );

    public static final List<String> INGREDIENTES_DISPONIBLES = Arrays.asList(
            "queso",
            "jamon",
            "pepperoni",
            "cebolla",
            "anchoas",
            "aceitunas negras",
            "maiz",
            "speck",
            "albahaca",
            "salami picante"
    );
    private int id;
    private String nombre;
    private String ingrediente;
    private String salsaBase;
    private String size;
    private double precio;
    private LocalDate fechaDelPedido;

    public Platillo(){

    }

    public Platillo(int id,String nombre, String ingrediente, String salsaBase, String size, double precio, LocalDate fechaDelPedido) {
        this.id = id;
        this.nombre = nombre;
        this.ingrediente = ingrediente;
        this.salsaBase = salsaBase;
        this.size = size;
        this.precio = precio;
        this.fechaDelPedido = fechaDelPedido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(String ingrediente) {
        this.ingrediente = ingrediente;
    }

    public String getSalsaBase() {
        return salsaBase;
    }

    public void setSalsaBase(String salsaBase) {
        this.salsaBase = salsaBase;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
