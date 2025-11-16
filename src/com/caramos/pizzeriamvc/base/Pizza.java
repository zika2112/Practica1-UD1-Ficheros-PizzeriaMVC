package com.caramos.pizzeriamvc.base;

import java.time.LocalDate;


public class Pizza extends Platillo{



    private String tipoMasa;

    public Pizza() {
    }

    public Pizza(int id, String nombre, String ingrediente, String salsaBase, String size, double precio, LocalDate fechaDelPedido, String tipoMasa) {
        super(id, nombre, ingrediente, salsaBase, size, precio, fechaDelPedido);
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
                " id = "+getId()+" Nombre = "+getNombre()+" Ingrediente = "+getIngrediente()+" Tamaño = "+getSize()+" Salsa base = "+getSalsaBase()+
                " Precio = "+getPrecio()+" Pedido hecho el = "+getFechaDelPedido()+" tipoMasa = " + tipoMasa  +
                '}';
    }
}
