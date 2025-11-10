package com.caramos.pizzeriamvc.gui;

import com.caramos.pizzeriamvc.base.Platillo;

import javax.swing.*;

public class Ventana {
    private JPanel panel1;
    private JRadioButton pizzaRadioButton;
    private JRadioButton calzoneRadioButton;
    private JTextField TipoDeSalsatextField;
    private JTextField IngredientestextField;
    private JTextField TamañoProductotextField;
    private JTextField PrecioProductotextField;
    private JButton crearButton;
    private JButton importarPedidosButton;
    private JButton exportarPedidosButton;
    private JList list1;

    public JFrame frame;
    //para poner los datos de vehiculos en la lista
    public DefaultListModel<Platillo> dlmPlatillo;


    public Ventana() {
        frame = new JFrame("VehiculosMVC");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        initComponents();
    }
    public void initComponents() {
        dlmPlatillo=new DefaultListModel<Platillo>();
        list1.setModel(dlmPlatillo);
    }
}

