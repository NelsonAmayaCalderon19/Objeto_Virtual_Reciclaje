/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author TURBO Core i3
 */
public class Conexion {
    /*public String db="ova";
    public String url="jdbc:mysql://node49201-objetovirtualreciclaje.jelastic.saveincloud.net/" +db;
    public String user="root";
    public String pass="FBYnxa76389";*/
	public String db="ova";
    public String url="jdbc:mysql://localhost/"+db;
    public String user="root";
    public String pass="";
    Connection link=null;
    public Conexion(){
        
    }
    
    public Connection Conexion(){
        
        try{
            Class.forName("org.gjt.mm.mysql.Driver");
            link=DriverManager.getConnection(this.url, this.user, this.pass);
        }catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
        return link;
    }
    public void cerrarConexion(){
        try{
            link.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
