package com.caramos.pizzeriamvc.gui;

import com.caramos.pizzeriamvc.base.Calzone;
import com.caramos.pizzeriamvc.base.Pizza;
import com.caramos.pizzeriamvc.base.Platillo;

import java.time.LocalDate;
import java.util.ArrayList;

import static com.caramos.pizzeriamvc.base.Calzone.FORMAS_DISPONIBLES;
import static com.caramos.pizzeriamvc.base.Pizza.TIPOS_MASA_DISPONIBLES;
import static com.caramos.pizzeriamvc.base.Platillo.*;

public class PizzeriaModelo {

    private ArrayList<Platillo> listaPlatillos;

    public ArrayList<Platillo> obtenerPlatillo(){
        return listaPlatillos;
    }

    public void altaPizza(String nombre, String ingredientes, String salsaBase, String tamaño, double precio,
                          LocalDate fechaDelPedido
            , String tipoMasa) {
        Pizza nuevaPizza = new Pizza(nombre,ingredientes,salsaBase,tamaño,precio,fechaDelPedido,tipoMasa);
        listaPlatillos.add(nuevaPizza);
    }

    public void altaCalzone(String nombre, String ingredientes, String salsaBase, String tamaño, double precio,
                            LocalDate fechaDelPedido,
                            String forma) {
        Calzone nuevoCalzone = new Calzone(nombre,ingredientes,salsaBase,tamaño,precio,fechaDelPedido,forma);
        listaPlatillos.add(nuevoCalzone);
    }

    public boolean existeIngrediente(String ingredientes) {
        for (Platillo unPlatillo: listaPlatillos) {
            if (unPlatillo.getIngredientes().equals(INGREDIENTES_DISPONIBLES)) {
                return true;
            }
        }
        return false;
    }
    public boolean existeSalsa(String salsa) {
        for (Platillo unPlatillo: listaPlatillos) {
            if (unPlatillo.getSalsaBase().equals(SALSAS_DISPONIBLES)) {
                return true;
            }
        }
        return false;
    }
    public boolean existeForma(String forma) {
        for (Platillo unPlatillo: listaPlatillos) {
            if (unPlatillo.getNombre().equals("pizza")) {
                return true;
            }
        }
        return false;
    }
    public boolean existeTipoDeMasa(String tipoMasa) {
        for (Platillo unPlatillo: listaPlatillos) {
            if (unPlatillo.getIngredientes().equals(TIPOS_MASA_DISPONIBLES)) {
                return true;
            }
        }
        return false;
    }
}
