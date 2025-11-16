package com.caramos.pizzeriamvc.gui;

import com.caramos.pizzeriamvc.base.Calzone;
import com.caramos.pizzeriamvc.base.Pizza;
import com.caramos.pizzeriamvc.base.Platillo;
import com.caramos.pizzeriamvc.util.Util;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

// Se modificaron las clases hayCamposVacios,limpiarCampos,addActionListener, se agregaron opciones nuevas en el switch
// y tambien cerrar la ventana y los valueChange

public class PizzeriaControlador implements ActionListener, ListSelectionListener, WindowListener {

    private Ventana vista;
    private PizzeriaModelo modelo;
    private File ultimaRutaExportada;

    public PizzeriaControlador(Ventana vista, PizzeriaModelo modelo) {
        this.vista = vista;
        this.modelo = modelo;

        addActionListener(this);
        addWindowListener(this);
        addListSelectionListener(this);
    }

    private boolean hayCamposVacios() {
        StringBuilder camposVacios = new StringBuilder();

        if (vista.idText.getText().isEmpty()) {
            camposVacios.append("\n- Id del platillo");
        }
        if (vista.nombreText.getText().isEmpty()) {
            camposVacios.append("\n- Nombre");
        }
        if (vista.listaIngredientesSeleccionados.getModel().getSize() == 0) {
            camposVacios.append("\n- Ingredientes");
        }
        if (vista.tipoDeSalsatextField.getSelectedItem() == null) {
            camposVacios.append("\n- Tipo de salsa");
        }
        if (vista.sizeProductoTextField.getText().isEmpty()) {
            camposVacios.append("\n- Tamaño");
        }
        if (vista.precioProductoTextField.getText().isEmpty()) {
            camposVacios.append("\n- Precio");
        }
        if (vista.fechaPedidoDatePicker.getText().isEmpty()) {
            camposVacios.append("\n- Fecha para el Pedido");
        }
        if (vista.tipoMasaFormatxt.getText().isEmpty()) {
            camposVacios.append("\n- Tipo de masa");
        }

        if (camposVacios.length() > 0) {
            Util.mensajeError("Los siguientes campos están vacíos: " + camposVacios.toString());
            return true;
        }

        return false;
    }

    private void limpiarCampos() {
        vista.idText.setText(null);
        vista.nombreText.setText(null);
        vista.dlmIngredientes.clear();
        vista.ingredientesComboBox.setSelectedIndex(-1);
        vista.tipoDeSalsatextField.setSelectedIndex(-1);
        vista.sizeProductoTextField.setText(null);
        vista.precioProductoTextField.setText(null);
        vista.fechaPedidoDatePicker.setText(null);
        vista.tipoMasaFormatxt.setText(null);
    }


    private void addActionListener(ActionListener listener) {
        vista.importarButton.addActionListener(listener);
        vista.exportarButton.addActionListener(listener);
        vista.crearButton.addActionListener(listener);
        vista.pizzaRadioButton.addActionListener(listener);
        vista.calzoneRadioButton.addActionListener(listener);
        vista.limpiarButton.addActionListener(listener);
        vista.agregarIngrediente.addActionListener(listener);
        vista.eliminarIngrediente.addActionListener(listener);
    }

    private void addWindowListener(WindowListener listener) {
        vista.frame.addWindowListener(listener);
    }


    private void addListSelectionListener(ListSelectionListener listener) {
        vista.list1.addListSelectionListener(listener);
    }

    public void refrescar() {
        vista.dlmPlatillo.clear();

        for (Platillo unPlatillo:modelo.obtenerPlatillo()) {
            vista.dlmPlatillo.addElement(unPlatillo);
        }
    }

    private void cargarDatosConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        configuracion.load(new FileReader("platillos.conf"));
        ultimaRutaExportada= new File(configuracion.getProperty("ultimaRutaExportada"));
    }

    private void actualizarDatosConfiguracion(File ultimaRutaExportada) {
        this.ultimaRutaExportada=ultimaRutaExportada;
    }

    private void guardarConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        if (ultimaRutaExportada != null) {
            configuracion.setProperty("ultimaRutaExportada",
                    ultimaRutaExportada.getAbsolutePath());
        }
        configuracion.store(new PrintWriter("platillos.conf"),
                "Datos de los platillos");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand=e.getActionCommand();

        switch (actionCommand) {
            case "Crear":
                if (hayCamposVacios()) {
                    break;
                }

                if (modelo.existeId(Integer.parseInt(vista.idText.getText()))) {
                    Util.mensajeError("Ya existe un platillo con este id" +
                            "\n" + vista.idText.getText());
                    break;
                }

                try {
                    int id = Integer.parseInt(vista.idText.getText());
                    double precio = Double.parseDouble(vista.precioProductoTextField.getText());

                    if (id < 0 || precio < 0) {
                        Util.mensajeError("El ID y el precio no pueden ser negativos");
                        break;
                    }

                    if (vista.pizzaRadioButton.isSelected()) {

                        modelo.altaPizza(id, vista.nombreText.getText(),
                                modelo.convertirListaIngredientesATexto(vista.listaIngredientesSeleccionados.getModel()),
                                (String) vista.tipoDeSalsatextField.getSelectedItem(),
                                vista.sizeProductoTextField.getText(),
                                precio,
                                vista.fechaPedidoDatePicker.getDate(),
                                vista.tipoMasaFormatxt.getText());
                        limpiarCampos();
                        refrescar();

                    } else {

                        modelo.altaCalzone(id, vista.nombreText.getText(),
                                modelo.convertirListaIngredientesATexto(vista.listaIngredientesSeleccionados.getModel()),
                                (String) vista.tipoDeSalsatextField.getSelectedItem(),
                                vista.sizeProductoTextField.getText(),
                                precio,
                                vista.fechaPedidoDatePicker.getDate(),
                                vista.tipoMasaFormatxt.getText());
                        limpiarCampos();
                        refrescar();
                    }

                } catch (NumberFormatException err) {
                    Util.mensajeError("El ID y el precio deben ser números válidos");
                }
                refrescar();

                break;
            case "Importar":
                JFileChooser selectorFichero = Util.crearSelectorFichero(ultimaRutaExportada
                        ,"Archivos XML","xml");
                int opt =selectorFichero.showOpenDialog(null);
                if (opt==JFileChooser.APPROVE_OPTION) {
                    try {
                        modelo.importarXML(selectorFichero.getSelectedFile());
                    } catch (ParserConfigurationException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (SAXException ex) {
                        ex.printStackTrace();
                    }
                    refrescar();
                }
                break;
            case "Exportar":
                JFileChooser selectorFichero2= Util.crearSelectorFichero(ultimaRutaExportada
                        ,"Archivos XML","xml");
                int opt2=selectorFichero2.showSaveDialog(null);
                if (opt2==JFileChooser.APPROVE_OPTION) {
                    try {
                        modelo.exportarXML(selectorFichero2.getSelectedFile());
                    } catch (ParserConfigurationException ex) {
                        ex.printStackTrace();
                    } catch (TransformerException ex) {
                        ex.printStackTrace();
                    }
                }
                break;
            case "Limpiar":
                limpiarCampos();
                break;
            case "Pizza":
                vista.tipoDeMasaFormaLbl.setText("Tipo masa");
                break;
            case "Calzone":
                vista.tipoDeMasaFormaLbl.setText("Forma");
                break;
            case "Agregar Ingrediente":
                if (vista.ingredientesComboBox.getSelectedItem() != null) {
                    String ingredienteSeleccionado = (String) vista.ingredientesComboBox.getSelectedItem();

                    if (!vista.dlmIngredientes.contains(ingredienteSeleccionado)) {
                        vista.dlmIngredientes.addElement(ingredienteSeleccionado);
                    }
                }
                break;

            case "Quitar Ingrediente":
                if (vista.listaIngredientesSeleccionados.getSelectedValue() != null) {
                    vista.dlmIngredientes.removeElement(vista.listaIngredientesSeleccionados.getSelectedValue());
                }
                break;
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {
        int resp = Util.mensajeConfirmacion("¿Desea cerrar la ventana?", "Salir");
        if (resp == JOptionPane.OK_OPTION) {
            try {
                guardarConfiguracion();
            } catch (Exception ex) {
                ex.printStackTrace();
                 JOptionPane.showMessageDialog(null, "Error al guardar configuración");
            } finally {
                System.exit(0);
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            Platillo platilloSeleccionado = (Platillo) vista.list1.getSelectedValue();
            vista.idText.setText(String.valueOf(platilloSeleccionado.getId()));
            vista.nombreText.setText(platilloSeleccionado.getNombre());
            String ingredientesTexto = platilloSeleccionado.getIngrediente();
            vista.dlmIngredientes.clear();
            if (ingredientesTexto != null && !ingredientesTexto.isEmpty()) {
                String[] ingredientesArray = ingredientesTexto.split(", ");
                for (String ingrediente : ingredientesArray) {
                    vista.dlmIngredientes.addElement(ingrediente.trim());
                }
            }
            vista.tipoDeSalsatextField.setSelectedItem(platilloSeleccionado.getSalsaBase());
            vista.sizeProductoTextField.setText(platilloSeleccionado.getSize());
            vista.precioProductoTextField.setText(String.valueOf(platilloSeleccionado.getPrecio()));
            vista.fechaPedidoDatePicker.setText(String.valueOf(platilloSeleccionado.getFechaDelPedido()));
            if (platilloSeleccionado instanceof Pizza) {
                vista.pizzaRadioButton.doClick();
                vista.tipoMasaFormatxt.setText(String.valueOf(((Pizza) platilloSeleccionado).getTipoMasa()));
            } else {
                vista.calzoneRadioButton.doClick();
                vista.tipoMasaFormatxt.setText(String.valueOf(((Calzone)platilloSeleccionado).getForma()));
            }
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }


    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
