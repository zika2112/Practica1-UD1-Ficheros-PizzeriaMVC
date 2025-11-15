package com.caramos.pizzeriamvc.gui;

import com.caramos.pizzeriamvc.base.Calzone;
import com.caramos.pizzeriamvc.base.Pizza;
import com.caramos.pizzeriamvc.base.Platillo;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;



public class PizzeriaModelo {

    private ArrayList<Platillo> listaPlatillos;

    public PizzeriaModelo() {
        listaPlatillos = new ArrayList<Platillo>();
    }

    public ArrayList<Platillo> obtenerPlatillo(){
        return listaPlatillos;
    }

    public void altaPizza(int id,String nombre, String ingredientes, String salsaBase, String size, double precio,
                          LocalDate fechaDelPedido
            , String tipoMasa) {
        Pizza nuevaPizza = new Pizza(id,nombre,ingredientes,salsaBase,size,precio,fechaDelPedido,tipoMasa);
        listaPlatillos.add(nuevaPizza);
    }

    public void altaCalzone(int id,String nombre, String ingredientes, String salsaBase, String size, double precio,
                            LocalDate fechaDelPedido,
                            String forma) {
        Calzone nuevoCalzone = new Calzone(id,nombre,ingredientes,salsaBase,size,precio,fechaDelPedido,forma);
        listaPlatillos.add(nuevoCalzone);
    }

    public boolean existeId (int id) {
        for (Platillo unPlatillo: listaPlatillos) {
            if (unPlatillo.getId() == id) {
                return true;
            }
        }
        return false;
    }
    public String convertirListaIngredientesATexto(ListModel<String> modeloLista) {
        StringBuilder ingredientes = new StringBuilder();

        for (int i = 0; i < modeloLista.getSize(); i++) {
            ingredientes.append(modeloLista.getElementAt(i));
            if (i < modeloLista.getSize() - 1) {
                ingredientes.append(", ");
            }
        }

        return ingredientes.toString();
    }

    public void exportarXML(File fichero) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        DOMImplementation dom = builder.getDOMImplementation();
        Document documento = dom.createDocument(null, "xml", null);


        Element raiz = documento.createElement("Platillo");
        documento.getDocumentElement().appendChild(raiz);

        Element nodoPlatillo=null;
        Element nodoDatos=null;
        Text texto=null;

        for (Platillo unPlatillo: listaPlatillos) {
            if (unPlatillo instanceof Pizza) {
                nodoPlatillo=documento.createElement("Pizza");
            } else {
                nodoPlatillo=documento.createElement("Calzone");
            }
            raiz.appendChild(nodoPlatillo);

            nodoDatos=documento.createElement("id");
            nodoPlatillo.appendChild(nodoDatos);

            texto=documento.createTextNode(String.valueOf(unPlatillo.getId()));
            nodoDatos.appendChild(texto);

            nodoDatos=documento.createElement("nombre");
            nodoPlatillo.appendChild(nodoDatos);

            texto=documento.createTextNode(unPlatillo.getNombre());
            nodoDatos.appendChild(texto);

            nodoDatos=documento.createElement("ingrediente");
            nodoPlatillo.appendChild(nodoDatos);

            texto=documento.createTextNode(unPlatillo.getIngrediente());
            nodoDatos.appendChild(texto);

            nodoDatos=documento.createElement("salsaBase");
            nodoPlatillo.appendChild(nodoDatos);

            texto=documento.createTextNode(unPlatillo.getSalsaBase());
            nodoDatos.appendChild(texto);

            nodoDatos=documento.createElement("size");
            nodoPlatillo.appendChild(nodoDatos);

            texto=documento.createTextNode(unPlatillo.getSize());
            nodoDatos.appendChild(texto);

            nodoDatos=documento.createElement("precio");
            nodoPlatillo.appendChild(nodoDatos);

            texto=documento.createTextNode(String.valueOf(unPlatillo.getPrecio()));
            nodoDatos.appendChild(texto);

            nodoDatos=documento.createElement("fechaDelPedido");
            nodoPlatillo.appendChild(nodoDatos);

            texto=documento.createTextNode(String.valueOf(unPlatillo.getFechaDelPedido()));
            nodoDatos.appendChild(texto);


            if (unPlatillo instanceof Pizza) {
                nodoDatos=documento.createElement("tipoMasa");
                nodoPlatillo.appendChild(nodoDatos);

                texto=documento.createTextNode(((Pizza) unPlatillo).getTipoMasa());
                nodoDatos.appendChild(texto);
            } else {
                nodoDatos=documento.createElement("forma");
                nodoPlatillo.appendChild(nodoDatos);

                texto=documento.createTextNode(((Calzone) unPlatillo).getForma());
                nodoDatos.appendChild(texto);
            }
        }

        Source source = new DOMSource(documento);
        Result result = new StreamResult(fichero);

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(source,result);


            }
    public void importarXML(File fichero) throws ParserConfigurationException, IOException, SAXException {
        listaPlatillos = new ArrayList<Platillo>();
        Pizza nuevaPizza = null;
        Calzone nuevoCalzone = null;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document documento = builder.parse(fichero);


        documento.getDocumentElement().normalize();

        NodeList listaElementos = documento.getElementsByTagName("*");

        for (int i=0;i<listaElementos.getLength();i++) {
            Element nodoPlatillo= (Element) listaElementos.item(i);

            if (nodoPlatillo.getTagName().equals("Pizza")) {
                nuevaPizza = new Pizza();

                nuevaPizza.setId(Integer.parseInt(nodoPlatillo.getChildNodes().item(0).getTextContent()));
                nuevaPizza.setNombre(nodoPlatillo.getChildNodes().item(1).getTextContent());
                nuevaPizza.setIngrediente(nodoPlatillo.getChildNodes().item(2).getTextContent());
                nuevaPizza.setSalsaBase(nodoPlatillo.getChildNodes().item(3).getTextContent());
                nuevaPizza.setSize(nodoPlatillo.getChildNodes().item(4).getTextContent());
                nuevaPizza.setPrecio(Double.parseDouble(nodoPlatillo.getChildNodes().item(5).getTextContent()));
                nuevaPizza.setFechaDelPedido(LocalDate.parse(nodoPlatillo.getChildNodes().item(6).getTextContent()));
                nuevaPizza.setTipoMasa(nodoPlatillo.getChildNodes().item(7).getTextContent());;
                listaPlatillos.add(nuevaPizza);

            } else if (nodoPlatillo.getTagName().equals("Calzone")) {
                nuevoCalzone = new Calzone();

                nuevoCalzone.setId(Integer.parseInt(nodoPlatillo.getChildNodes().item(0).getTextContent()));
                nuevoCalzone.setNombre(nodoPlatillo.getChildNodes().item(1).getTextContent());;
                nuevoCalzone.setIngrediente(nodoPlatillo.getChildNodes().item(2).getTextContent());;
                nuevoCalzone.setSalsaBase(nodoPlatillo.getChildNodes().item(3).getTextContent());;
                nuevoCalzone.setSize(nodoPlatillo.getChildNodes().item(4).getTextContent());;
                nuevoCalzone.setPrecio(Double.parseDouble(nodoPlatillo.getChildNodes().item(5).getTextContent()));
                nuevoCalzone.setFechaDelPedido(LocalDate.parse(nodoPlatillo.getChildNodes().item(6).getTextContent()));
                nuevoCalzone.setForma(nodoPlatillo.getChildNodes().item(7).getTextContent());
                listaPlatillos.add(nuevoCalzone);
            }
        }
    }
}


