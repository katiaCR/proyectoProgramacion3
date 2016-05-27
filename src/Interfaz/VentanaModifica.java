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
 * @author katia abigail
 */
public class VentanaModifica extends Ventana{

    Almacen almacen;
    JLabel etiquetas [];
    JTextField camposText [];
    DataBase db;
    
    public VentanaModifica (DataBase db) {
        this.db=db;
    }
    
    @Override
    public void setTitle() {
        this.setTitle("Modifica datos almacén");
    }

    @Override
    public void confirmar() {
        try{            
            if(tienenValor()){
                if(camposText[2].getText().length()<10 && camposText[3].getText().length()<10){
                    if (telfValido() && codPosValido() && idValido()) {
                        almacen = new Almacen(Integer.parseInt(camposText[0].getText()),camposText[1].getText(),camposText[2].getText(),camposText[3].getText(),Integer.parseInt(camposText[4].getText()));
                        System.out.println("DATOS MODIFICADOS:");
                        if(db.modifica(almacen)){
                            System.out.println("ha sido modificado");
                            limpiaVentana();
                        }else{
                            ventanaError("no se ha podido modificar");
                        }
                    }
                }else{
                    if(!idValido()){
                        ventanaError("id incorrecto");
                    }else{
                        ventanaError("El máximo de caracteres es 10 en teléfono y código_postal");
                    }
                }
            }
        }catch(NumberFormatException e){
            ventanaError("No se ha podido modificar datos incorrectos");
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
        cuerpo.setLayout(new GridLayout(5,2,5,5));    
        
        String nombreEtiquetas [] = {"id", "razón social", "sede social", "telefono contacto", "codigo postal"};               
        etiquetas = new JLabel [nombreEtiquetas.length];        
        camposText =new JTextField[nombreEtiquetas.length];
        
        for (int x=0;x<nombreEtiquetas.length;x++) {
            camposText[x] = new JTextField();
            camposText[x].setName(nombreEtiquetas[x]);
            etiquetas[x]=new JLabel(nombreEtiquetas[x]);
            cuerpo.add(etiquetas[x]);
            cuerpo.add(camposText[x]);            
       }                
        contenedor.add(cuerpo);
    }
    
    private boolean idValido(){
        try{
            Integer.parseInt(camposText[0].getText());
            return true;
        }catch(NumberFormatException e){
            throw e;
        }
    }
    
    private boolean telfValido(){
        try{
            Integer.parseInt(camposText[3].getText());
            return true;
        }catch(NumberFormatException e){
            throw e;
        }
    }
    private boolean codPosValido(){
        try{
            Integer.parseInt(camposText[4].getText());
            return true;
        }catch(NumberFormatException e){
            throw e;
        }
    }
    
    private boolean tienenValor(){
        for (int x=0;x<camposText.length;x++) {
            if(camposText[x].getText().length() > 0){
                return true;              
            }
        }
        ventanaError("Todos los campos son obligatorios");
        return false;
    }
    
    void limpiaVentana(){
        for(int x=0; x<camposText.length;x++){
            camposText[x].setText(null);
        }
    }
    
}
