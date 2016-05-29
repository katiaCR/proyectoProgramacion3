package Datos;

/**
 * Esta clase se encarga de crear un Pedido
 * @author katia abigail
 * @version 27/05/2016
 */
public class Pedido {
    int pedid;
    String fecha;

    /**
     * Este constructor sirve para crear un pedido
     * @param pedid
     * @param fecha 
     */
    public Pedido(int pedid, String fecha) {
        this.pedid = pedid;
        this.fecha = fecha;
    }
    
    /**
     * Devuelve los datos de un pedido
     * @return Array tipo String con los datos
     */
    public String[] getArrayPedido(){
      String[] array = new String[2];
      array[0]=String.valueOf(pedid);
      array[1]=fecha;
      
      return array;
    }
}
