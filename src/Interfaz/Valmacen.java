/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Datos.DataBase;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Esta clase se encarga de crear una ventana
 * para pedir el id valido de un almacén  y generar un listado
 * @author katia abigail
 * @version 27/05/2016
 */
public class Valmacen extends Ventana{

    JLabel etiquetaID;
    JTextField almid;
    DataBase db;
    
    public Valmacen(DataBase db) {
        this.db=db;
        this.setLocation(500, 100);
        this.setSize(260, 125);
    }

    @Override
    public void setTitle() {
        this.setTitle("Almacén");
    }

    @Override
    public void confirmar() {
        try{
            if(almid.getText().length()>0){
              if(idValido()){  
                if(!(db.listado3(Integer.parseInt(almid.getText())).isEmpty())){                    
                    VentanaListado3 v2 = new VentanaListado3(db,Integer.parseInt(almid.getText()));
                    limpiaVentana();
                }else{
                    ventanaError("No existen pedidos de ese Almacén");
                }
              }  
            }else{
                ventanaError("tienes que introducir un almacen_id");
            }     
        }catch(NumberFormatException e){
            ventanaError("valor del id inválido");
        }
    }
    
    @Override
    public void textoBotonAux() {
        botonAux.setText("Cancelar");
    }

    @Override
    public void aux() {
        this.dispose();
    }

    @Override
    public void creaCuerpo() {
        
        cuerpo.setLayout(new GridLayout(1,2,5,5));              
        etiquetaID = new JLabel("almacén id: ");        
        almid =new JTextField();        
            cuerpo.add(etiquetaID);
            cuerpo.add(almid);      
        contenedor.add(cuerpo);
    }
    
    private boolean idValido(){
        try{
            Integer.parseInt(almid.getText());
            return true;
        }catch(NumberFormatException e){
            throw e;
        }
    }
    
     void limpiaVentana(){
        almid.setText(null);
    }
}
