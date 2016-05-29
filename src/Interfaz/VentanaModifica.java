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
 * Esta clase crea un ventana para modificar los datos de un almacén
 * @author katia abigail
 * @version 27/05/2016
 */
public class VentanaModifica extends Ventana{

    Almacen almacen;
    JLabel etiquetas [];
    JTextField camposText [];
    DataBase db;
    
    /**
     * Este contructor se encarga de agregar una BD y configurar el tamaño y posicion de la ventana
     * @param db Base de datos
     */
    public VentanaModifica (DataBase db) {
        this.db=db;
        this.setLocation(760, 720);
        this.setSize(400, 300);
    }
    
    @Override
    public void setTitle() {
        this.setTitle("Modifica datos almacén");
    }

    @Override
    public void confirmar() {
        if(tienenValor()){
            if(idValido()){
                if(db.existeIdAlmacen(Integer.parseInt(camposText[0].getText()))){
                    if(rsYssValido()){
                        if(telfYcodValido()){
                            almacen = new Almacen(Integer.parseInt(camposText[0].getText()),camposText[1].getText(),camposText[2].getText(),camposText[3].getText(),Integer.parseInt(camposText[4].getText()));
                            if(db.modifica(almacen)){
                                System.out.println("Datos Modificados");
                                System.out.println("Nuevos Datos:");
                                almacen.muestraDatos();
                                limpiaVentana();
                            }else{
                                ventanaError("No se ha podido modificar");
                            }
                        }
                    }
                }else{
                    ventanaError("No existe un almacen con ese id");
                }                
            }
        }
             
    }

    @Override
    public void textoBotonAux() {
        botonAux.setText("Cancelar");
    }

    @Override
    /**
     * Este metodo se encarga de darle una funcion al boton aux
     */
    public void aux() {
         System.out.println("Cancelar");
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
    
    /**
     * comprueba el id
     * @return true si es valido
     */
    private boolean idValido(){
        try{
            if(camposText[0].getText().length()<5){
                Integer.parseInt(camposText[0].getText());
                return true;
            }else {
                ventanaError("el máximo de caracteres del id es 4");
                return false;
            }
        }catch(NumberFormatException e){
            ventanaError("Id no válido");
            return false;
        }
    }
    
    /**
     * Comprueba si todos los campos tienen un valor
     * @return true si tienen valor
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
     * @return true si son validos
     */
    private boolean telfYcodValido(){
        try{
            if(camposText[3].getText().length()<11 && camposText[4].getText().length()<11){
               Integer.parseInt(camposText[3].getText());
               Integer.parseInt(camposText[4].getText());
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
     * Comprueba si razon social y sede social son validos
     * @return true si son validos
     */
    private boolean rsYssValido(){
        if(camposText[1].getText().length()<31){
            if(camposText[2].getText().length()<51){
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
     * Pone a nul los campos
     */
    void limpiaVentana(){
        for(int x=0; x<camposText.length;x++){
            camposText[x].setText(null);
        }
    }
    
}
