package com.caramos.pizzeriamvc.base;

import java.time.LocalDate;

public class Calzone extends Platillo{

    private String forma;

    public Calzone() {
    }

    public Calzone(String nombre, String ingredientes, String salsaBase, String tamaño, double precio, LocalDate fechaDelPedido, String forma) {
        super(nombre, ingredientes, salsaBase, tamaño, precio, fechaDelPedido);
        this.forma = forma;
    }

    public String getForma() {
        return forma;
    }

    public void setForma(String forma) {
        this.forma = forma;
    }

    @Override
    public String toString() {
        return "Calzone{" +
                " Nombre= "+getNombre()+" Ingredientes = "+getIngredientes()+" Salsa base = "+getSalsaBase()+
                " Precio = "+getPrecio()+" Pedido hecho el = "+getFechaDelPedido()+" tipoMasa = " + forma  +
                '}';
    }
}

