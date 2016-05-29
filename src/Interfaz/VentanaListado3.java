/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Datos.DataBase;
import Datos.Pedido;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.ListIterator;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Esta clase muestra un listado de los pedidos hecho por un almacen
 * @author katia abigail
 * @version 27/05/2016
 */
public class VentanaListado3 extends JFrame{
    
    JPanel contenedor;
    JTable table;
    DefaultTableModel modelo;
    ArrayList pedidos;
    DataBase db;
    int almid;
    
    
    public VentanaListado3(DataBase db, int almid) {
        this.almid=almid;
        pedidos= new ArrayList();
        this.db=db;
        this.setTitle("Listado Pedidos");
        this.setVisible(true);
        initComponents();
        this.pack();
        this.setLocation(1200, 600);
        this.setSize(600,200);
        
    }    
     public void initComponents() {
        pedidos=db.listado3(almid);
        contenedor = (JPanel) this.getContentPane();
        String[] cabecera = {"Nº Pedido","Fecha"};
        modelo = new DefaultTableModel(cabecera,0);
        table = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(table);
        contenedor.add(scrollPane, BorderLayout.CENTER);      
        muestraFilas();
    }
     
     /**
      * Agrega una fila por cada pedido en el ArrayList pedidos
      */
     public void muestraFilas() {
        Pedido pe;
        ListIterator <Pedido> it =pedidos.listIterator();
        while(it.hasNext()){
            pe= it.next();
            modelo.addRow(pe.getArrayPedido());
        }
     }  
}
