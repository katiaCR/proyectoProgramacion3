/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import oracle.jdbc.OracleDriver;

/**
 * Esta clase se encarga de la gestión de la Base de Datos
 * @author katia abigail
 * @version 16/05/2016
 */
public class DataBase {
    
    String servidor= "jdbc:oracle:thin:@localhost:";
    String bd;
    String password;
    String user;
    Connection conexion=null;
    
    /**
     * Realiza la conexion a la base de datos con el usuario y password correspondiente
     * @param bd
     * @param user
     * @param password 
     */
    public DataBase(String bd, String user, String password) {
        this.bd = bd;
        this.user = user;
        this.password = password;
    }
    
    /**
     * Abre la conexion a la base de datos
     * @return true si se ha podido conectar
     */
    public boolean abrirConexion() {
        boolean estado = false;
        
        try {
            DriverManager.registerDriver(new OracleDriver());
            conexion = DriverManager.getConnection(servidor + bd, user, password);
            estado = true;
        } catch (SQLException ex) { System.out.println("SQL Exception: " +ex.getLocalizedMessage());
        } catch (Exception ex) { System.out.println("Exception General: " +ex.getLocalizedMessage());
        }
        
        return estado;
    }
    
    /**
     * Se encarga de dar de alta a un almacen en la base de datos
     * @param al 
     */
     public void alta(Almacen al){
        PreparedStatement st;
        //sustituimos las variables por un ?        
        String sentencia = 
        "INSERT INTO almacenes (id_almacen, razon_social , sede_social, telefono_contacto, codigo_postal) VALUES (id_alm_sec.NEXTVAL,?,?,?,?)";
        
        String datos[]=al.getDatos();
        System.out.println(sentencia);
        try{
            st= conexion.prepareStatement(sentencia);
            st.setString(1,datos[0]);
            st.setString(2,datos[1]);
            st.setString(3,datos[2]);
            st.setInt(4,al.getCodPostal());
            System.out.println("Alta: " + st.toString());
            st.executeUpdate();
            st.close();        
            
        } catch (SQLException ex) {
            System.out.println("Error con la base de datos: " + ex.getMessage());
        }
    }
    
     /**
      * Se encarga de dar de baja a un almacen en la base de datos
      * @param al
      * @return 
      */
     public int baja(Almacen al){      
        int n=0;
        PreparedStatement st;
        String sentencia = "DELETE FROM almacenes WHERE id_almacen = ?";
         
         try{
            st= conexion.prepareStatement(sentencia);
            st.setInt(1,al.getId());
            System.out.println("Baja: " + st.toString());
            n = st.executeUpdate();
            st.close();        
        } catch (SQLException ex) {
            System.out.println("Error con la base de datos: " + ex.getMessage());
        }
        finally {
            return n;
        }
    }
    
     /**
      * Comprueba si exite un almacen con ese id en la BD
      * @param id
      * @return 
      */
    public boolean existeIdAlmacen(int id){
        Almacen al= null;
        ResultSet rs;
        PreparedStatement st;
        String sentencia = "SELECT * from almacenes where id_almacen =?";
        System.out.println(sentencia);
        try{
            st= conexion.prepareStatement(sentencia);
            st.setInt(1,id);
            rs=st.executeQuery();
            if(rs.next()){
                al=new Almacen(rs.getInt(1),rs.getString(3), rs.getString(5), rs.getString(6), rs.getInt(7));
                System.out.println("Id: " + al.getId());
            }else{
                return false;
            }
            rs.close();
            st.close();            
        } catch (SQLException ex) {
            System.out.println("Error con la base de datos: " + ex.getMessage());
        }
        return true;
    }
    
    /**
     * Busca un almacen en la BD
     * @param id
     * @return el almacen buscado, si no es encontrado devuelve null
     */
    public Almacen buscaAlmacen(int id){
        Almacen al= null;
        ResultSet rs;
        PreparedStatement st;
        String sentencia = "SELECT * from almacenes where id_almacen =?";
        System.out.println(sentencia);
        try{
            st= conexion.prepareStatement(sentencia);
            st.setInt(1,id);
            rs=st.executeQuery();
            if(rs.next()){
                al=new Almacen(rs.getInt(1),rs.getString(3), rs.getString(5), rs.getString(6), rs.getInt(7));
                System.out.println("Id: " + al.getId());
                al.muestraDatos();
            }
            rs.close();
            st.close();            
        } catch (SQLException ex) {
            System.out.println("Error con la base de datos: " + ex.getMessage());
        }
        return al;
    }    
    
    /**
     * Se encarga de modificar un almacen en la base de datos
     * @param al
     * @return true si ha sido modifiado
     */
    public boolean modifica(Almacen al) {
        PreparedStatement st;
        String datos[]=al.getDatos();
        String sentencia = "UPDATE almacenes set razon_social= ? , sede_social= ? , telefono_contacto= ? , codigo_postal=? where ID_ALMACEN = ? ";
         
         try{
            st= conexion.prepareStatement(sentencia);
            st.setString(1,datos[0]);
            st.setString(2,datos[1]);
            st.setString(3,datos[2]);
            st.setInt(4,al.getCodPostal());
            st.setInt(5,al.getId());
            
            st.executeUpdate();
            
            System.out.println("Modifica: " + st.toString());     
            st.close();  
            return true;
        } catch (SQLException ex) {
            System.out.println("Error con la base de datos: " + ex.getMessage());
        }
         return false;
    }
    
    /**
     * Cierra la conexion a la base de datos
     */
    public void cerrarConexion() {
        try {
            conexion.close();
        } catch(SQLException e) { System.out.println(e.getMessage());
        }
    }
    
    public void cierraResultSet(ResultSet rs) {
        try {
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error con la base de datos: " + ex.getMessage());
        }
    }
    
    /**
     * Busca todos los almacenes en la base de datos en orden alfabetico,
     * y los almacen cada uno en un objeto almacen
     * @return un ArrayList con objetos tipo Almacen
     */
    public ArrayList <Almacen> listado1(){
        ResultSet rs= null;
        PreparedStatement st =null;
        ArrayList <Almacen> listado=new ArrayList<>();
        String sentencia="select * from almacenes order by RAZON_SOCIAL";
        try {
            st=conexion.prepareStatement(sentencia);                
            rs=st.executeQuery();
           while(rs.next()){
                Almacen al=new Almacen(rs.getInt(1),rs.getString(3), rs.getString(5), rs.getString(6), rs.getInt(7));
                listado.add(al);
            }
            st.close();                
            rs.close();
            } catch (SQLException ex) {
                System.out.println("Error al cargar datos");
            }
            finally{
                return listado;
            }    
    }
    
    /**
     * Busca en la BD según el id del producto todos los almacenes que hayan pedido alguna vez ese producto
     * @param prodid
     * @return un ArrayList de tipo Almacen
     */
    public ArrayList <Almacen> listado2(int prodid){
        ResultSet rs= null;
        PreparedStatement st =null;
        ArrayList <Almacen> listado=new ArrayList<>();
        String sentencia="select al.* from almacenes al join HISTORICO_VENTAS hv on (al.ID_ALMACEN=hv.almacenes_id_almacen) join LINEA_HISTORICO_VENTAS lv on (hv.ID_HIS_VEN=lv.historico_ventas_id_his_ven) where lv.PRODUCTOS_ID_PROD=?";
        try {
            st=conexion.prepareStatement(sentencia);
            st.setString(1,String.valueOf(prodid));
            rs=st.executeQuery();
           while(rs.next()){
                Almacen al=new Almacen(rs.getInt(1),rs.getString(3), rs.getString(5), rs.getString(6), rs.getInt(7));
                listado.add(al);
            }            
            st.close();
            rs.close();
            } catch (SQLException ex) {
                System.out.println("Error al cargar datos");
            }
            finally{
                return listado;
            }
    }
    
    /**
     * Busca los pedidos en la base de datos según el almacen
     * @param almid
     * @return un ArrayList de tipo Pedido
     */
    public ArrayList <Pedido> listado3(int almid){
        ResultSet rs= null;
        PreparedStatement st =null;
        ArrayList <Pedido> listado=new ArrayList<>();
        String sentencia="select ID_PED_ALMA ,FECHA from PEDIDO_ALMACEN where ALMACENES_ID_ALMACEN=?";
        try {
            st=conexion.prepareStatement(sentencia);
            st.setString(1,String.valueOf(almid));
            rs=st.executeQuery();
           while(rs.next()){
                Pedido pe=new Pedido(rs.getInt(1),rs.getString(2));
                listado.add(pe);
            }            
            st.close();
            rs.close();
            } catch (SQLException ex) {
                System.out.println("Error al cargar datos");
            }
            finally{
                return listado;
            }
    }
}
