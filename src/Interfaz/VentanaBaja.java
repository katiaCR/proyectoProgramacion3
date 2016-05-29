package Interfaz;

import Datos.DataBase;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Esta clase crea una ventana que da de baja a un almacen
 * @author katia abigail
 * @version 15/05/2016
 */
public class VentanaBaja extends Ventana{

    JLabel etiquetaID;
    JTextField id;
    DataBase db;
    
    public VentanaBaja(DataBase db) {
        this.db=db;  
        this.setLocation(1250, 500);
        this.setSize(260, 125);
    }

    @Override
    public void setTitle() {
        this.setTitle("Baja Almacén");
    }

    @Override
    public void confirmar() {
        
        if(id.getText().length()>0 && id.getText().length()<5){
            if(idValido()){
                if(db.existeIdAlmacen(Integer.parseInt(id.getText()))){
                    VentanaConfirma vc = new VentanaConfirma(db, Integer.parseInt(id.getText()));
                    limpiaVentana();
                }else{
                    ventanaError("No existe un almacén con ese id");
                }
            }
        }else{
            ventanaError("tienes que introducir un id valido, máximo 4 caracteres");
        }
    }
    
    @Override
    public void textoBotonAux() {
        botonAux.setText("Cancelar");
    }

    @Override
    public void aux() {
        System.out.println("Cancelar");
        this.dispose();
    }

    @Override
    public void creaCuerpo() {          
        cuerpo.setLayout(new GridLayout(1,2,5,5));                  
        etiquetaID = new JLabel("almacén id: ");        
        id =new JTextField();        
            cuerpo.add(etiquetaID);
            cuerpo.add(id);            
        contenedor.add(cuerpo);    
    }
    
    /**
     * Comprueba el id
     * @return boolean
     */
    private boolean idValido(){
        try{
            Integer.parseInt(id.getText());
            return true;
        }catch(NumberFormatException e){
            ventanaError("valor del id inválido");
            return false;
        }
    }
    
    /**
     * Limpia los campos
     */
    void limpiaVentana(){
        id.setText(null);
    }
}
