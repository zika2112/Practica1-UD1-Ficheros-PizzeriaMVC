package com.caramos.pizzeriamvc.base;

import java.time.LocalDate;

public class Pizza extends Platillo{

    private String tipoMasa;

    public Pizza() {
    }

    public Pizza(String nombre, String ingredientes, String salsaBase, String tamaño, double precio, LocalDate fechaDelPedido, String tipoMasa) {
        super(nombre, ingredientes, salsaBase, tamaño, precio, fechaDelPedido);
        this.tipoMasa = tipoMasa;
    }

    public String getTipoMasa() {
        return tipoMasa;
    }

    public void setTipoMasa(String tipoMasa) {
        this.tipoMasa = tipoMasa;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                " Nombre= "+getNombre()+" Ingredientes = "+getIngredientes()+" Salsa base = "+getSalsaBase()+
                " Precio = "+getPrecio()+" Pedido hecho el = "+getFechaDelPedido()+" tipoMasa = " + tipoMasa  +
                '}';
    }
}
