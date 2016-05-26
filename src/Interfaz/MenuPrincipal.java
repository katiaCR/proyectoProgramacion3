package Interfaz;

import Datos.DataBase;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

/*
Listado de almacenes ordenado alfabéticamente
Introducir un producto y mostrar los almacenes a los que se ha servido.
Introducir un almacén y mostrar sus pedidos (nº pedido y fecha)
*/





/**
 * Esta clase muestra el Menú Principal con un listado de opciones relacionados
 * a la gestión de un Almacen
 * @author katia abigail
 * @version 14/05/2016
 */
public class MenuPrincipal extends JFrame implements ActionListener, WindowListener {
    //JPanel contenedor;
    ImagePanel contenedor;
    JButton botones[];
    DataBase db;
    BufferedImage myImage;
    
    public MenuPrincipal(DataBase db) {
        this.db=db;
        try {            
             myImage = ImageIO.read(new File("imagenes/fondo1.jpg"));
        } catch (IOException ex) {
            System.out.println("No se ha encontrado imagen");
        }
        //this.db=db;
        this.setContentPane(new ImagePanel(myImage));
        this.setTitle("Gestión Almacenes");
        this.setVisible(true);
        initComponents();         
        this.pack();
        this.setSize(350,250);
    }
    
    private void initComponents() {
        String textoBotones[]={"Alta Almacen", "Baja Almacen","Modificación", "Listados","Crear Documento XML","Fin"};
        
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
           
           //AGREGAR UNA IMAGEN EN UN BOTON
           //http://javacursoavanzado.blogspot.com.es/2012/10/poner-imagen-boton-java.html
           //botones[x].set
           contenedor.add(botones[x]);
       }
        //Atiendo a la ventana
       this.addWindowListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(db.abrirConexion()) {
            switch (e.getActionCommand()) {
                case "0":
                    VentanaAlta v1 = new VentanaAlta(db);
                    break;
                case "1":
                    VentanaBaja vb1= new VentanaBaja(db);
                    break;
                case "2":
                    VentanaModifica vm1 = new VentanaModifica(db);
                    break;
                case "3":
                    VentanaListado vl1= new VentanaListado (db);
                    break;
                case "4":
                    break;                
                default:
                    fin();
                    break;
                
            }
        
        }        
    }

    private void fin() {
        //db.cerrarConexion();
        System.exit(0);
    }
    @Override
    public void windowOpened(WindowEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("cerrando");
        fin();
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosed(WindowEvent e) {
        System.out.println("Cerrado");
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowIconified(WindowEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowActivated(WindowEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
