/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Datos.Almacen;
import Datos.DataBase;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author katia abigail
 */
public class VentanaListado extends JFrame implements ActionListener{

    ImagePanel contenedor;
    ArrayList <Almacen> almacenes;
    JButton [] botones;
    BufferedImage myImage;
    DataBase db;
    
    public VentanaListado(DataBase db) {
        this.db=db;
        almacenes = new ArrayList<>();
        try {            
             myImage = ImageIO.read(new File("imagenes/fondo2.jpg"));
        } catch (IOException ex) {
            System.out.println("No se ha encontrado imagen");
        }
        this.almacenes=almacenes;
        this.setContentPane(new ImagePanel(myImage));
        this.setTitle("Gestión Almacenes");
        this.setVisible(true);
        initComponents();         
        this.pack();
        this.setSize(350,250);
    }   
    
    private void initComponents() {
        String textoBotones[]={"Listado Almacen", 
            "Listado por producto","Pedidos por almacén"};
        
        botones=new JButton[textoBotones.length];
        //Utilizo todo el fondo del JFrame
        contenedor=(ImagePanel) this.getContentPane();
        //Inicializo un layout
        contenedor.setLayout(new GridLayout(textoBotones.length, 1, 5, 5));
        
        //Inicializo los objetos
       for (int x=0;x<textoBotones.length;x++) {
           botones[x]=new JButton();
           botones[x].setText(textoBotones[x]);
           botones[x].setActionCommand(Integer.toString(x));
           botones[x].addActionListener(this);
           
           contenedor.add(botones[x]);
       }
    }
    @Override
    public void actionPerformed(ActionEvent e) {

            switch (e.getActionCommand()) {
                case "0":
                    VentanaListado1 v1 = new VentanaListado1(db.listado1());
                    break;
                case "1":                    
                    Vproducto vp= new Vproducto(db);
                    break;
                case "2":
                    Valmacen va = new Valmacen(db);
                    break;                                
                default:
                    this.dispose();
                    break;
            }
        }    
}
