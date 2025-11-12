package com.caramos.pizzeriamvc.base;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Pizza extends Platillo{

    public static final List<String> TIPOS_MASA_DISPONIBLES = Arrays.asList(
            "fina",
            "gruesa",
            "rellena de queso",
            "rellena de queso ricotta"
    );

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
