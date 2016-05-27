/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

/**
 *
 * @author Alumno
 */
public class Pedido {
    int pedid;
    String fecha;

    public Pedido(int pedid, String fecha) {
        this.pedid = pedid;
        this.fecha = fecha;
    }
    
    public String[] getArrayPedido(){
      String[] array = new String[2];
      array[0]=String.valueOf(pedid);
      array[1]=fecha;
      
      return array;
    }
}
