/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

/**
 * Esta clase crea y contiene los datos de un Almacén
  * @author katia abigail
  * @version 28/05/2016
 */
public class Almacen {
    private int id;
    private String razonSocial;
    private String sedeSocial;
    private String telf;
    private int codPostal;

    /**
     * Este contructor es para dar de alta a un almacen
     * @param razonSocial
     * @param sedeSocial
     * @param telf
     * @param codPostal 
     */
    public Almacen(String razonSocial, String sedeSocial, String telf, int codPostal) {
        this.razonSocial = razonSocial;
        this.sedeSocial = sedeSocial;
        this.telf = telf;
        this.codPostal = codPostal;
    }
    
    /**
     * Este constructor es para mostrar un almacen en un listado con todos sus datos
     * @param id
     * @param razonSocial
     * @param sedeSocial
     * @param telf
     * @param codPostal 
     */
    public Almacen(int id, String razonSocial, String sedeSocial, String telf, int codPostal) {
        this.id = id;
        this.razonSocial = razonSocial;
        this.sedeSocial = sedeSocial;
        this.telf = telf;
        this.codPostal = codPostal;
    }
    
    /**
     * Este constructor es para buscar o borrar un almacen que coincida con ese id
     * @param id 
     */
    public Almacen(int id){
        this.id=id;
    }
    
    public void muestraDatos(){
        System.out.println(
                " razon_social: " + razonSocial +
                " sede_social: " + sedeSocial +
                " teléfono_contacto: " + telf +
                " código_postal: " + codPostal);    
    }
    
    /**
     * Devuelve los datos tipo String del almacen
     * @return array de String con los datos
     */
    public String[] getDatos(){
        String[] datos=new String[3];
        datos[0]=getRazonSocial();
        datos[1]=getSedeSocial();
        datos[2]=getTelf();        
        return datos;
    }
    
    /**
     * Convierte a String todos los datos del almacen
     * @return un array de String con los datos
     */
    public String[] getArrayAlmacen(){
      String[] array = new String[5];
      array[0]=String.valueOf(id);
      array[1]=razonSocial;
      array[2]=sedeSocial;
      array[3]=telf;
      array[4]=String.valueOf(codPostal);
      
      return array;
    }
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the razonSocial
     */
    public String getRazonSocial() {
        return razonSocial;
    }

    /**
     * @return the sedeSocial
     */
    public String getSedeSocial() {
        return sedeSocial;
    }

    /**
     * @return the telf
     */
    public String getTelf() {
        return telf;
    }

    /**
     * @return the codPostal
     */
    public int getCodPostal() {
        return codPostal;
    }
    
    
    
}
