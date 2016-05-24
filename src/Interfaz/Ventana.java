/*
 * Ventana para obtener los datos de los alumnos
 */
package Interfaz;
;
import Datos.DataBase;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
TODAS LAS VENTANAS A CREAR SERAN EXTENDS DE VENTANA
*/
public abstract class Ventana extends JFrame implements ActionListener {

    protected JPanel contenedor, cuerpo, botonesMenu;
    protected JButton botonFin, botonAux;    

    public Ventana() {
        contenedor = (JPanel) this.getContentPane();
        setTitle();
        this.setLocation(500, 500);
        this.setVisible(true);
        contenedor.setLayout(new BorderLayout());
        initComponents();
        this.pack();
    }

    public void initComponents()
    {
        cuerpo = new JPanel();
        botonesMenu = new JPanel();
        creaCuerpo();
        creaBotones();
    }
    
   
    private void creaBotones() {
        //Declaro el JPanel botones como un gridlayour de 1 fila
        botonesMenu.setLayout(new GridLayout(1,2,5,5));
        //Creará los botones dejando el botón auxiliar sin texto 
        //para que lo defina la clase que lo herede.
        botonFin=new JButton("Confirmar");
        botonFin.setActionCommand("Confirmar");
        botonFin.addActionListener(this);
        
        botonAux=new JButton("");
        //pongo el texto en el botón aux que lo tendrá que hacer
        //la ventana que lo herede
        textoBotonAux();
        botonAux.setActionCommand("Aux");
        botonAux.addActionListener(this);
        //pongo los 2 botones en el JPanel
        botonesMenu.add(botonFin);
        botonesMenu.add(botonAux);
        //añado el panel al contenedor
        contenedor.add(botonesMenu, BorderLayout.SOUTH);  
    }
    
    //Estos dos métodos son obligatorios para todas las ventanas
    //Que hereden esta ventana
    
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
                //System.out.println("Aux");
                aux();
                break;
        }
    }

}
