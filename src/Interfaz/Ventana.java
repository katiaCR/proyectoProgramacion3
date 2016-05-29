package Interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Esta clase abstracta permite crear una ventana y heredar metodos obligatorios
 * @author katia abigail
 * @version 14/05/2016
 */
public abstract class Ventana extends JFrame implements ActionListener {

    protected JPanel contenedor, cuerpo, botonesMenu;
    protected JButton botonFin, botonAux;    

    /**
     * Se encarga de crear el contenedor principal
     */
    public Ventana() {
        contenedor = (JPanel) this.getContentPane();
        setTitle();
        this.setLocation(500, 500);
        this.setVisible(true);
        contenedor.setLayout(new BorderLayout());
        initComponents();
        this.pack();
    }

    /**
     * Inicializa los componentes del contenedor
     */
    public void initComponents()
    {
        cuerpo = new JPanel();
        botonesMenu = new JPanel();
        creaCuerpo();
        creaBotones();
    }
    
   /**
    * Crea los botones 
    */
    private void creaBotones() {
        botonesMenu.setLayout(new GridLayout(1,2,5,5));
        botonFin=new JButton("Confirmar");
        botonFin.setActionCommand("Confirmar");
        botonFin.addActionListener(this);
        
        botonAux=new JButton("");
        textoBotonAux();
        botonAux.setActionCommand("Aux");
        botonAux.addActionListener(this);
        botonesMenu.add(botonFin);
        botonesMenu.add(botonAux);
        contenedor.add(botonesMenu, BorderLayout.SOUTH);  
    }
    
    //agrega un titulo a la ventana
    public abstract void setTitle();
            
    //cierra la ventana
    public abstract void confirmar();
    
    //pone el texto en el boton Aux
    public abstract void textoBotonAux();
    
    //método que se ejecutará cuando se pulse el botón aux
    public abstract void aux();
       
    //método que crea el cuerpo de la ventana   
    public abstract void creaCuerpo();
    
    public void ventanaError(String cadena) {
        JOptionPane.showMessageDialog(
                this, cadena,
                "Error", JOptionPane.INFORMATION_MESSAGE);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case "Confirmar":
                System.out.println("Confirmar");
                confirmar();
                break;
            case "Aux":
                aux();
                break;
        }
    }

}
