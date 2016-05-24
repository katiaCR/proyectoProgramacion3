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
public class VentanaBaja extends Ventana{

    JLabel etiquetaID;
    JTextField id;
    DataBase db;
    
    public VentanaBaja(DataBase db) {
        this.db=db;
    }

    @Override
    public void setTitle() {
        this.setTitle("Baja Almacén");
    }

    @Override
    public void confirmar() {
        try{
            if(id.getText().length()>0){
                if(idValido()){
                    if(db.baja(new Almacen(Integer.parseInt(id.getText())))>0){
                        System.out.println("Almacen dado de baja con exito");
                        limpiaVentana();
                    }else{
                        ventanaError("No se ha encontrado ningún almacén con ese id");
                    }                    
                }
            }else{
                ventanaError("tienes que introducir un id");
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
        etiquetaID = new JLabel("id: ");        
        id =new JTextField();        
            cuerpo.add(etiquetaID);
            cuerpo.add(id);            
        contenedor.add(cuerpo);    
    }
    
    private boolean idValido(){
        try{
            Integer.parseInt(id.getText());
            return true;
        }catch(NumberFormatException e){
            throw e;
        }
    }
    
     void limpiaVentana(){
        id.setText(null);
    }
}
