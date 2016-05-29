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
 * Esta clase crea una ventana que da de alta a un almacen
 * @author katia abigail
 * @version 15/05/2016
 */
public class VentanaAlta extends Ventana{
    
    Almacen newAlmacen;
    JLabel etiquetas [];
    JTextField camposText [];
    DataBase db;
    
    public VentanaAlta(DataBase db) {
        this.db=db;
        this.setLocation(300, 400);
        this.setSize(400, 300);
    }
    
    @Override
    public void setTitle() {
        this.setTitle("Alta Almacén");
    }
    
    @Override
    public void confirmar() {        
       if(tienenValor()){                
            if(rsYssValido()){
                if(telfYcodValido()){
                    newAlmacen = new Almacen(camposText[0].getText(),camposText[1].getText(),camposText[2].getText(),Integer.parseInt(camposText[3].getText()));
                    System.out.println("Nuevo Almacén:");
                    newAlmacen.muestraDatos();
                    db.alta(newAlmacen);
                    limpiaVentana();
                }
            }
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
    
    /**
     * Comprueban si todos los campos tienen un valor
     * @return boolean
     */
    private boolean tienenValor(){
        for (int x=0;x<camposText.length;x++) {
            if(camposText[x].getText().length() < 1){
                ventanaError("Todos lo campos son obligatorios");
                return false;              
            }
        }
        return true;
    }
    
    /**
     * Comprueba si el telefono y el codigo postal son validos
     * @return boolean
     */
    private boolean telfYcodValido(){
        try{
            if(camposText[2].getText().length()<11 && camposText[3].getText().length()<11){
               Integer.parseInt(camposText[2].getText());
               Integer.parseInt(camposText[3].getText());
               return true;
            }
            else{
                ventanaError("El máximo de caracteres es 10 en teléfono y código postal");
                return false;
            }
        }catch(NumberFormatException e){
            ventanaError("valor del teléfono o código postal invalido");
            return false;
        }
    }
    
    /**
     * Comprueba si la razon social y la sede social son validos
     * @return boolean
     */
    private boolean rsYssValido(){
        if(camposText[0].getText().length()<31){
            if(camposText[1].getText().length()<51){
                return true;
            }else {
                ventanaError("El máximo de caracteres es 50 en sede social");
                return false;
            }
        }else {
            ventanaError("El máximo de caracteres es 30 en razón social");
            return false;
        }
    }
    
    /**
     * Limpia los campos
     */
    void limpiaVentana(){
        for(int x=0; x<camposText.length;x++){
            camposText[x].setText(null);
        }
    }
}
