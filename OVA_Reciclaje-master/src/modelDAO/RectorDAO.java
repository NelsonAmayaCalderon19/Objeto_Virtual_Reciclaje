/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelDAO;

import Config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.Rector;

/**
 *
 * @author NELSON
 */
public class RectorDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;
    public Rector validar(String usuario, String contraseña){
    Rector us = new Rector();
    String sql ="select * from admin where Usuario=? and Contraseña=?";
    try{
    con = cn.Conexion();
    ps=con.prepareStatement(sql);
    ps.setString(1, usuario);
    ps.setString(2, contraseña);
    rs=ps.executeQuery();
    while(rs.next()){
    us.setUsuario(rs.getString("Usuario"));
    us.setPassword(rs.getString("Contraseña"));
    us.setColegio(rs.getInt("id_Colegio"));
    }
    }catch(Exception e){   
    }   
    return us;
    }
    
}
