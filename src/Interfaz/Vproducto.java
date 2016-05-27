/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Datos.Almacen;
import Datos.DataBase;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Alumno
 */
public class Vproducto extends Ventana{

    JLabel etiquetaID;
    JTextField prodid;
    DataBase db;
    
    public Vproducto(DataBase db) {
        this.db=db;
    }

    @Override
    public void setTitle() {
        this.setTitle("Producto");
    }

    @Override
    public void confirmar() {
        try{
            if(prodid.getText().length()>0){
              if(idValido()){  
                if(!(db.listado2(Integer.parseInt(prodid.getText())).isEmpty())){                    
                    VentanaListado2 v2 = new VentanaListado2(db,Integer.parseInt(prodid.getText()));
                    limpiaVentana();
                }else{
                    ventanaError("No existen almacenes que hayan pedido ese producto");
                }
              }  
            }else{
                ventanaError("tienes que introducir un producto_id");
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
        //EL ID debe ser borrardo y en alta poner insert into (sec.nexvalue)               
        etiquetaID = new JLabel("producto id: ");        
        prodid =new JTextField();        
            cuerpo.add(etiquetaID);
            cuerpo.add(prodid);      
        contenedor.add(cuerpo);
    }
    
    private boolean idValido(){
        try{
            Integer.parseInt(prodid.getText());
            return true;
        }catch(NumberFormatException e){
            throw e;
        }
    }
    
     void limpiaVentana(){
        prodid.setText(null);
    }
}
