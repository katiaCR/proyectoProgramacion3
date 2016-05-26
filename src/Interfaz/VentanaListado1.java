/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Datos.Almacen;
import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ListIterator;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alumno
 */
public class VentanaListado1 extends JFrame{
    
    JPanel contenedor;
    JTable table;
    DefaultTableModel modelo;
    ArrayList <Almacen> almacenes;
    
    public VentanaListado1(ArrayList<Almacen> almacenes) {
        
        this.almacenes=almacenes;   
        this.setTitle("Listado");
        this.setVisible(true);
        initComponents();
        this.pack();
        this.setSize(600,300);
        
    }
    
     public void initComponents() {
        contenedor = (JPanel) this.getContentPane();
        String[] cabecera = {"Id","Razón Social","Sede Social","Telefono Contacto","Código Postal"};
        modelo = new DefaultTableModel(cabecera,0);
        table = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(table);
        contenedor.add(scrollPane, BorderLayout.CENTER);
        muestraFilas();
    }
     
     public void muestraFilas() {
        Almacen al;
        ListIterator <Almacen> it =almacenes.listIterator();
        while(it.hasNext()){
            al= it.next();
            modelo.addRow(al.getArrayAlmacen());
        }
    }
}
