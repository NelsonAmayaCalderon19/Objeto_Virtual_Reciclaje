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
import modelo.Docente;


/**
 *
 * @author NELSON
 */
public class DocenteDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    public Docente validar(String usuario, String contraseņa){
    Docente us = new Docente();
    String sql ="select * from docente where codigo=? and contraseņa=? or cedula=? and contraseņa=?";
    try{
    con = cn.Conexion();
    ps=con.prepareStatement(sql);
    ps.setString(1, usuario);
    ps.setString(2, contraseņa);
    ps.setString(3, usuario);
    ps.setString(4, contraseņa);
    
    rs=ps.executeQuery();
    while(rs.next()){
    us.setId(rs.getInt("id"));
    us.setNombre(rs.getString("nombre"));
    us.setCedula(rs.getString("cedula"));
    us.setCodigo(rs.getString("codigo"));
    us.setEmail(rs.getString("email"));
    us.setPassword(rs.getString("Contraseņa"));
    us.setColegio(rs.getInt("id_Colegio"));
    }
    }catch(Exception e){   
    }   
    return us;
    }
    
    public ResultSet listarTodos( String id){
    String sql=("select * from docente where id_Colegio="+id);   
         try {
             con=cn.Conexion();
             ps=con.prepareStatement(sql);
             rs=ps.executeQuery();
         } catch (Exception ex) {           
         }   
    return rs;
    };
    
     public int agregar(Docente p){
    String sql="insert into docente(nombre, cedula, codigo, email, contraseņa, id_Colegio) values(?,?,?,?,?,?)";
    try{
        con=cn.Conexion();
    ps=con.prepareStatement(sql);
    ps.setString(1, p.getNombre());
    ps.setString(2, p.getCedula());
    ps.setString(3, p.getCodigo());
    ps.setString(4, p.getEmail());
    ps.setString(5, p.getPassword());
    ps.setInt(6, p.getColegio());
    ps.executeUpdate();
    }catch(Exception ex){
    }
    return r;
    }
     public void delete(String id){
    String sql="delete from docente where id="+id;
    try{
    con=cn.Conexion();
    ps=con.prepareStatement(sql);
    ps.executeUpdate();
    }catch(Exception ex){
    }
    }
     
     public int actualizarPassword(Docente c, String id){
         
    	    String sql ="update docente set contraseņa=? where id="+id;
    	    try{
    	        con=cn.Conexion();
    	    ps=con.prepareStatement(sql);
    	    ps.setString(1, c.getPassword());
    	    ps.executeUpdate();
    	    }catch(Exception ex){
    	    }
    	    return r;
    	    }
}

