/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_3;

import Datos.DataBase;
import Interfaz.MenuPrincipal;

/**
 *
 * @author katia abigail
 */
public class Proyecto_3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String bd= "1521:xe";
        String user= "central";      
        String password= "central";
        
        DataBase db = new DataBase(bd, user, password);
        
        if(db.abrirConexion()) {
            System.out.println("NOS HEMOS CONECTADO!!!!!");
            MenuPrincipal v1= new MenuPrincipal(db);       
        }
        else{
            System.out.println("No se ha podido conectar");
        }
        //MenuPrincipal v1= new MenuPrincipal();
    }
    
}
