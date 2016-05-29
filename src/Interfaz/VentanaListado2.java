/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Datos.Almacen;
import Datos.DataBase;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.ListIterator;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Esta clase muestra un listado de los almacenes segun el producto que hayan pedido
 * @author katia abigail
 * @version 27/05/2016
 */
public class VentanaListado2 extends JFrame{
    
    JPanel contenedor;
    JTable table;
    DefaultTableModel modelo;
    ArrayList <Almacen> almacenes;
    DataBase db;
    int prodid;
    
    
    public VentanaListado2(DataBase db, int prodid) {
        this.prodid=prodid;
        almacenes= new ArrayList<>();
        this.db=db;
        this.setTitle("Listado Almacenes por Pedido");
        this.setVisible(true);
        initComponents();
        this.pack();
        this.setLocation(1200, 400);
        this.setSize(600,200);
        
    }    
     public void initComponents() {
        almacenes=db.listado2(prodid);
        contenedor = (JPanel) this.getContentPane();
        String[] cabecera = {"Id","Razón Social","Sede Social","Telefono Contacto","Código Postal"};
        modelo = new DefaultTableModel(cabecera,0);
        table = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(table);
        contenedor.add(scrollPane, BorderLayout.CENTER);      
        muestraFilas();
    }
     
     
     /**
      * Agrega una fila por cada almacen en el ArrayList almacenes
      */
     public void muestraFilas() {
        Almacen al;
        ListIterator <Almacen> it =almacenes.listIterator();
        while(it.hasNext()){
            al= it.next();
            modelo.addRow(al.getArrayAlmacen());
        }
     }       
}