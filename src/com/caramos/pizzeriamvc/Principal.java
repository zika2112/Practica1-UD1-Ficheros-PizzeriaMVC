package com.caramos.pizzeriamvc;

import com.caramos.pizzeriamvc.gui.PizzeriaControlador;
import com.caramos.pizzeriamvc.gui.PizzeriaModelo;
import com.caramos.pizzeriamvc.gui.Ventana;

public class Principal {
    public static void main(String[] args) {
        Ventana vista = new Ventana();
        PizzeriaModelo modelo = new PizzeriaModelo();
        PizzeriaControlador controlador = new PizzeriaControlador(vista,modelo);
    }
}
