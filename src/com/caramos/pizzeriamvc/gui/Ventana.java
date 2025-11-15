package com.caramos.pizzeriamvc.gui;

import com.caramos.pizzeriamvc.base.Platillo;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.*;

public class Ventana {
    private JPanel panel1;
     JRadioButton pizzaRadioButton;
     JRadioButton calzoneRadioButton;
     JComboBox<String> tipoDeSalsatextField;
     JTextField ingredientesTextField;
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
     JTextField sizeProductoTextField;
     JButton agregarIngrediente;
     JButton eliminarIngrediente;
     JList<String> listaIngredientesSeleccionados;
     JComboBox<String> ingredientesComboBox;

    public JFrame frame;
    public DefaultListModel<Platillo> dlmPlatillo;
    public DefaultListModel<String> dlmIngredientes;

    ImageIcon img = new ImageIcon("food.png");
    ImageIcon background = new ImageIcon("fondo.png");

    JPanel backgroundPanel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    };

    public Ventana() {

        frame = new JFrame("PizzeriaMVC");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setIconImage(img.getImage());
        frame.setContentPane(backgroundPanel);
        backgroundPanel.setLayout(new BorderLayout());
        panel1.setOpaque(false);
        backgroundPanel.add(panel1, BorderLayout.CENTER);
        initComponents();
    }

    public void initComponents() {

        dlmPlatillo=new DefaultListModel<Platillo>();
        list1.setModel(dlmPlatillo);

        DefaultComboBoxModel<String> modeloSalsas = new DefaultComboBoxModel<>();
        for (String salsa : Platillo.SALSAS_DISPONIBLES) {
            modeloSalsas.addElement(salsa);
        }
        tipoDeSalsatextField.setModel(modeloSalsas);
        tipoDeSalsatextField.setSelectedIndex(-1);

        DefaultComboBoxModel<String> modeloIngredientes = new DefaultComboBoxModel<>();
        for (String ingrediente : Platillo.INGREDIENTES_DISPONIBLES) {
            modeloIngredientes.addElement(ingrediente);
        }
        ingredientesComboBox.setModel(modeloIngredientes);
        ingredientesComboBox.setSelectedIndex(-1);

        dlmIngredientes = new DefaultListModel<>();
        listaIngredientesSeleccionados.setModel(dlmIngredientes);
    }


}

