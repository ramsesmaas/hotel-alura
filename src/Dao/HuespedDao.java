/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Modelos.Huesped;
import Modelos.Reserva;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import logica.CalcularReserva;

/**
 *
 * @author maast
 */
public class HuespedDao {

    private Connection con;
    int id;

    public HuespedDao(Connection con) {
        this.con = con;
    }

    public int guardar(Huesped huesped) {
        try {
            PreparedStatement statement;
            statement = con.prepareStatement(
                    "INSERT INTO huespedes "
                    + "(Nombre, Apellido, Fecha_de_nacimiento, Nacionalidad, Telefono, idReserva)"
                    + " VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            try (statement) {
                statement.setString(1, huesped.getNombre());
                statement.setString(2, huesped.getApellido());
                statement.setObject(3, huesped.getFechaNacimiento());
                statement.setString(4, huesped.getNacionalidad());
                statement.setInt(5, huesped.getTelefono());
                statement.setInt(6, huesped.getIdResrva());

                statement.execute();

                final ResultSet resultSet = statement.getGeneratedKeys();

                try (resultSet) {  //try with resources
                    while (resultSet.next()) {
                       // huesped.setId(resultSet.getInt(1));

                        //if (resultSet.next()) {
                            id = resultSet.getInt(1);
                        //}
                        System.out.println(String.format("Fue insertado el huesped id : %s, %s", huesped,id));
                    }
                }

                //ResultSet generatedKeys = statement.getGeneratedKeys();
                return id;

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    
       public List<Huesped> listarHuespedes() throws SQLException{
        List<Huesped> resultado = new ArrayList<>();
      
        try {
             PreparedStatement statement;
             statement =  con.prepareStatement("select id,Nombre, Apellido, Fecha_de_nacimiento, Nacionalidad, Telefono, idReserva from huespedes");
             
             try(statement){
                 statement.execute();
                 
                 final ResultSet resultSet = statement.getResultSet();
                 
                 try(resultSet){
                     while (resultSet.next()) {
                         LocalDate fechaNacimiento = CalcularReserva.convertToLocalDateViaSqlDate(resultSet.getDate("Fecha_de_nacimiento"));
                         
                         
                        Huesped fila = new Huesped(resultSet.getInt("id"),
                                 resultSet.getString("Nombre"),
                                 resultSet.getString("Apellido"),
                                fechaNacimiento,
                                resultSet.getString("Nacionalidad"),
                                resultSet.getInt("Telefono"),
                                resultSet.getInt("idReserva")
                        );
                         resultado.add(fila);
                     }
                 
                 }
             }
             System.out.println("resultado: " + resultado);
             return resultado;
        } catch (SQLException e) {
             throw new RuntimeException(e);
        }
       
        
    }
       
      public List<Huesped> listarHuespedesPorBusqueda(String input) throws SQLException{
        List<Huesped> resultado = new ArrayList<>();
      
        try {
             PreparedStatement statement;
             statement =  con.prepareStatement("SELECT * FROM huespedes WHERE id LIKE ? OR Nombre LIKE ? OR Apellido LIKE ? OR Fecha_de_nacimiento LIKE ? OR Nacionalidad LIKE ?"
                     + "                        OR Telefono LIKE ? OR idReserva LIKE ?");
             statement.setString(1, input + "%");
             statement.setString(2, input + "%");
             statement.setString(3, input + "%");
             statement.setString(4, input + "%");
             statement.setString(5, input + "%");
             statement.setString(6, input + "%");
             statement.setString(7, input + "%");
             try(statement){
                 statement.execute();
                 
                 final ResultSet resultSet = statement.getResultSet();
                 
                 try(resultSet){
                     while (resultSet.next()) {
                          LocalDate fechaNacimiento = CalcularReserva.convertToLocalDateViaSqlDate(resultSet.getDate("Fecha_de_nacimiento"));
                         
                         
                        Huesped fila = new Huesped(resultSet.getInt("id"),
                                 resultSet.getString("Nombre"),
                                 resultSet.getString("Apellido"),
                                fechaNacimiento,
                                resultSet.getString("Nacionalidad"),
                                resultSet.getInt("Telefono"),
                                resultSet.getInt("idReserva")
                        );
                         resultado.add(fila);
                     }
                 
                 }
             }
             
             return resultado;
        } catch (SQLException e) {
             throw new RuntimeException(e);
        }
       
        
    }  
      
       public int eliminarHuesped(int idHuesped){
       
           try {
               PreparedStatement statement;
               statement = con.prepareStatement("DELETE FROM huespedes WHERE id = ?");
               
               try(statement){
                   statement.setInt(1, idHuesped);
                   statement.execute();
                   
                   int updateCount = statement.getUpdateCount();
                   System.out.println("se elimina huesped: " + updateCount);
                   return updateCount;
                   
               }
               
           } catch (SQLException e) {
               System.out.println("no se elimina");
               throw new RuntimeException(e);
               
           }
       
       }
       
          public int actualizarHuesped(Huesped huesped) {
        
        int respuesta = 1;
        try {
            PreparedStatement statement;
            statement = con.prepareStatement("UPDATE huespedes SET Nombre = ?,Apellido = ?,Fecha_de_nacimiento = ?,Nacionalidad = ?, Telefono = ?, idReserva = ? WHERE id = ?");
            
            try (statement) {
                 
                statement.setString(1, huesped.getNombre());
                statement.setString(2, huesped.getApellido());
                statement.setObject(3, huesped.getFechaNacimiento());
                statement.setString(4, huesped.getNacionalidad());   //?
                statement.setInt(5, huesped.getTelefono());
                statement.setInt(6, huesped.getIdResrva());
                statement.setInt(7, huesped.getId());
                 respuesta = statement.executeUpdate();

              

                

                

            }
        } catch (SQLException e) {
          //  respuesta = 0;
            
        }
        
        return respuesta;
    }
}
