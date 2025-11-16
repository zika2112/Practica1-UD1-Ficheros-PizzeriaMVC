package com.caramos.pizzeriamvc.base;

import java.time.LocalDate;


public class Calzone extends Platillo{


    private String forma;

    public Calzone() {
    }

    public Calzone(int id, String nombre, String ingrediente, String salsaBase, String size, double precio, LocalDate fechaDelPedido, String forma) {
        super(id, nombre, ingrediente, salsaBase, size, precio, fechaDelPedido);
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
                " id = "+getId()+" Nombre= "+getNombre()+" Ingrediente = "+getIngrediente()+" Tamaño = "+getSize()+" Salsa base = "+getSalsaBase()+
                " Precio = "+getPrecio()+" Pedido hecho el = "+getFechaDelPedido()+" tipoMasa = " + forma  +
                '}';
    }
}

