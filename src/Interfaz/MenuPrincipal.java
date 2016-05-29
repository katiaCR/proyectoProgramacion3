package Interfaz;

import Datos.DataBase;
import Datos.DocumentoXML;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Esta clase muestra el Menú Principal con un listado de opciones relacionados a la gestión de un Almacen
 * @author katia abigail
 * @version 14/05/2016
 */
public class MenuPrincipal extends JFrame implements ActionListener, WindowListener {

    ImagePanel contenedor;
    JButton botones[];
    DataBase db;
    BufferedImage myImage;
    
    /**
     * Este contructor se encarga de configurar los aspectos de la ventana y agregar la BD
     * @param db 
     */
    public MenuPrincipal(DataBase db) {
        this.db=db;
        try {            
             myImage = ImageIO.read(new File("imagenes/fondo1.jpg"));
        } catch (IOException ex) {
            System.out.println("No se ha encontrado imagen");
        }        
        this.setContentPane(new ImagePanel(myImage));
        this.setTitle("Gestión Almacenes");
        this.setVisible(true);
        initComponents();    
        this.pack();
        this.setLocation(800, 420);
        this.setSize(350,250);
    }
    
    /**
     * Agrega los componentes a la ventana
     */
    private void initComponents() {
        String textoBotones[]={"Alta Almacen", "Baja Almacen","Modificar Datos", "Listados","Crear Documento XML","Fin"};
        
        botones=new JButton[textoBotones.length];
        contenedor=(ImagePanel) this.getContentPane();
        contenedor.setLayout(new GridLayout(textoBotones.length, 1, 5, 5));
        
       for (int x=0;x<textoBotones.length;x++) {
           botones[x]=new JButton();
           botones[x].setText(textoBotones[x]);
           botones[x].setActionCommand(Integer.toString(x));
           botones[x].addActionListener(this);
           
           contenedor.add(botones[x]);
       }
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
                    DocumentoXML.escribo("Almacenes",db.listado1());
                    break;                
                default:
                    fin();
                    break;
                
            }
        
        }        
    }

    /**
     * Cierra la ventana y la conexion a la base de datos
     */
    private void fin() {
        db.cerrarConexion();
        System.exit(0);
    }
    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("cerrando");
        fin();
    }

    @Override
    public void windowClosed(WindowEvent e) {
        System.out.println("Cerrado");
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
