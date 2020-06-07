
package com.emergentes.utiles;
import java.sql.*;
public class ConexionBD {
    static String driver = "com.mysql.jdbc.Driver";
    static String url = "jdbc:mysql://localhost:3306/bd_almacen";
    static String usuario = "root";
    static String password = "";
    
    protected Connection conn = null;
    public ConexionBD(){
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, usuario, password);
            if (conn != null) {
                System.out.println("coneccion exitosa");
            }
            
        } catch (ClassNotFoundException e) {
            System.out.println("Error de driver"+ e.getMessage());
        }catch (SQLException e){
            System.out.println("Error al conectar"+e.getMessage());
        }
    }
    
    public Connection conectar(){
        return conn;
    }
    public void desconectar(){
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error al cerra la base de datos");
        }
    }
    
}
