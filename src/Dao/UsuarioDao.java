/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Modelos.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author maast
 */
public class UsuarioDao {
      private Connection con;
    

    public UsuarioDao(Connection con) {
        this.con = con;
    }
    
    public Usuario obtenerUsuarioPorNombre(String nombre, String contrasena){
    Usuario usuario = new Usuario(0,"","");
    
      try {
             PreparedStatement statement;
             statement =  con.prepareStatement("SELECT * FROM usuarios where nombre = ? and contrasena = ? limit 1");
             statement.setString(1, nombre);
             statement.setString(2,contrasena);
             
             try(statement){
                 statement.execute();
                 
                 final ResultSet resultSet = statement.getResultSet();
                 
                 try(resultSet){
                     while (resultSet.next()) {
                          
                         
                       usuario = new Usuario(resultSet.getInt("id"),resultSet.getString("nombre"),resultSet.getString("contrasena") );  
                       
                        
                         
                     }
                 
                 }
             }
             
             return usuario;
        } catch (SQLException e) {
             throw new RuntimeException(e);
        }
    
    }
}
