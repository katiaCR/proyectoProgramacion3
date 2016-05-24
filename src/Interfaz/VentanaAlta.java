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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Alumno
 */
public class VentanaAlta extends Ventana{
    
    Almacen newAlmacen;
    JLabel etiquetas [];
    JTextField camposText [];
    DataBase db;

    public VentanaAlta(DataBase db) {
        this.db=db;
    }
    
    @Override
    public void setTitle() {
        this.setTitle("Alta Almacén");
    }
    
    @Override
    public void confirmar() {
        try{            
            if(tienenValor()){
                if(camposText[2].getText().length()<10 && camposText[3].getText().length()<10){
                    if (telfValido() && codPosValido() ) {
                        newAlmacen = new Almacen(camposText[0].getText(),camposText[1].getText(),camposText[2].getText(),Integer.parseInt(camposText[3].getText()));
                        System.out.println("NUEVOS DATOS:");
                        newAlmacen.muestraDatos();
                        db.alta(newAlmacen);
                        limpiaVentana();
                    }
                }else{
                    ventanaError("El máximo de caracteres es 10 en teléfono y código_postal");
                }
            }
        }catch(NumberFormatException e){
            ventanaError("No se ha podido dar de alta datos incorrectos");
        }                
    }

    @Override
    public void textoBotonAux() {
        botonAux.setText("Cancelar");
    }

    @Override
    //método que se ejecutará cuando se pulse el botón aux
    public void aux() {
        this.dispose();
    }

    @Override
    public void creaCuerpo() {
        cuerpo.setLayout(new GridLayout(4,2,5,5));        
        
        String nombreEtiquetas [] = {"razón social", "sede social", "telefono contacto", "codigo postal"};               
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
    
    private boolean tienenValor(){
        for (int x=0;x<camposText.length;x++) {
            if(camposText[x].getText().length() > 0){
                return true;              
            }
        }
        ventanaError("Todos lo campos son obligatorios");
        return false;
    }
    
    private boolean telfValido(){
        try{
            Integer.parseInt(camposText[2].getText());
            return true;
        }catch(NumberFormatException e){
            throw e;
        }
    }
    
    private boolean codPosValido(){
        try{
            Integer.parseInt(camposText[3].getText());
            return true;
        }catch(NumberFormatException e){
            throw e;
        }
    }
    
    void limpiaVentana(){
        for(int x=0; x<camposText.length;x++){
            camposText[x].setText(null);
        }
    }
}
