package com.caramos.pizzeriamvc.gui;

import com.caramos.pizzeriamvc.base.Platillo;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;

public class Ventana {
    private JPanel panel1;
     JRadioButton pizzaRadioButton;
     JRadioButton calzoneRadioButton;
     JTextField tipoDeSalsatextField;
     JTextField ingredientesTextField;
     JTextField tamañoProductoTextField;
     JTextField precioProductoTextField;
     JButton crearButton;
     JButton importarButton;
     JButton exportarButton;
     JList list1;
     JTextField idText;
     JTextField nombreText;
     DatePicker fechaPedidoDatePicker;
     JTextField tipoMasaFormatxt;
     JLabel tipoDeMasaFormaLbl;
     JLabel fechaParaElPedidoLabel;
     JButton limpiarButton;

    public JFrame frame;
    public DefaultListModel<Platillo> dlmPlatillo;


    public Ventana() {
        frame = new JFrame("PizzeriaMVC");
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

