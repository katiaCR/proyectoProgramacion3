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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Esta clase crea una ventana que confirma la baja de un almacén
 * @author katia abigail
 * @version 29/05/2016
 */
public class VentanaConfirma extends JFrame implements ActionListener{

    JButton confirmar, cancelar;
    JPanel contenedor;
    JLabel mensaje;
    DataBase db;
    int id;
    
    public VentanaConfirma(DataBase db, int id) {
        contenedor = (JPanel) this.getContentPane();
        this.db=db;        
        this.id=id;
        this.setSize(300, 200);
        this.setTitle("Confirmar Baja");
        this.setVisible(true);
        initComponents();         
        this.pack();
        this.setLocation(800, 100);
        this.setSize(400,200);
    }

    /**
     * Borra el almacen según su id
     */
    public void confirmar() {
        if(db.baja(new Almacen(id))>0){
            System.out.println("Almacen dado de baja con exito");
        }else{
            ventanaError("No se ha podido dar de baja");
        }
    }
    
    /**
     * Agrega los componentes a la ventana
     */
    public void initComponents() {
        contenedor.setLayout(new GridLayout(3,1,5,5));
        db.buscaAlmacen(id).muestraDatos();        
        mensaje= new JLabel("Se va a borrar el almacén: " 
                + db.buscaAlmacen(id).getRazonSocial() 
                + "  de la sede: " + db.buscaAlmacen(id).getSedeSocial());
        
        cancelar=new JButton("Cancelar");
        cancelar.setActionCommand("Cancelar");
        cancelar.addActionListener(this);
        
        confirmar=new JButton("Confirmar");
        confirmar.setActionCommand("Confirmar");
        confirmar.addActionListener(this);
        
        contenedor.add(mensaje);        
        contenedor.add(confirmar);
        contenedor.add(cancelar);
    }
    
    /**
     * Crea un ventana error
     * @param cadena informe del error
     */
    public void ventanaError(String cadena) {
        JOptionPane.showMessageDialog(
                this, cadena,
                "Error", JOptionPane.INFORMATION_MESSAGE);
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case "Confirmar":
                confirmar();
                this.dispose();
                break;
            case "Cancelar":
                this.dispose();
                break;
        }
    }
        
}

