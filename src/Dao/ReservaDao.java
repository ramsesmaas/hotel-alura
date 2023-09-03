/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Modelos.Reserva;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.time.LocalTime;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import logica.CalcularReserva;
import logica.Conexion;

public class ReservaDao {

    private Connection con;
    int id = 0;

    public ReservaDao(Connection con) {
        this.con = con;
    }

    public int guardar(Reserva reserva) {
        try {
            PreparedStatement statement;
            statement = con.prepareStatement(
                    "INSERT INTO reservas "
                    + "(Fecha_Entrada, Fecha_Salida, Valor, Forma_De_Pago)"
                    + " VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            try (statement) {
                statement.setObject(1, reserva.getFechaEntrada());
                statement.setObject(2, reserva.getFechaSalida());
                statement.setDouble(3, reserva.getValor());
                statement.setString(4, reserva.getFormaDePago());   //?

                statement.execute();

                final ResultSet resultSet = statement.getGeneratedKeys();

                try (resultSet) {
                    //while (resultSet.next()) {
                       // reserva.setId(resultSet.getInt(1));
                      // id = resultSet.getInt(1);
                       // System.out.println(String.format("Fue insertado el producto: %s, %s", reserva,id));
                    //}
                    if (resultSet.next()) {
                    id = resultSet.getInt(1);
                    System.out.println(String.format("Fue insertado el producto: %s, %s", reserva,id));
               }
                    
                }

               // ResultSet generatedKeys = statement.getGeneratedKeys();
                //if (generatedKeys.next()) {
                   // id = generatedKeys.getInt(1);
               // }

                return id;

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Reserva> listarReservas() throws SQLException {
        List<Reserva> resultado = new ArrayList<>();

        try {
            PreparedStatement statement;
            statement = con.prepareStatement("select id,Fecha_Entrada, Fecha_Salida, Valor, Forma_De_Pago from reservas");

            try (statement) {
                statement.execute();

                final ResultSet resultSet = statement.getResultSet();

                try (resultSet) {
                    while (resultSet.next()) {
                        LocalDate d1 = CalcularReserva.convertToLocalDateViaSqlDate(resultSet.getDate("Fecha_Entrada"));
                        LocalDate d2 = CalcularReserva.convertToLocalDateViaSqlDate(resultSet.getDate("Fecha_Salida"));

                        Reserva fila = new Reserva(resultSet.getInt("id"),
                                d1,
                                d2,
                                resultSet.getDouble("Valor"),
                                resultSet.getString("Forma_De_Pago")
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

    public List<Reserva> listarReservasPorBusqueda(String input) throws SQLException {
        List<Reserva> resultado = new ArrayList<>();

        try {
            PreparedStatement statement;
            statement = con.prepareStatement("SELECT * FROM reservas WHERE id LIKE ? OR Fecha_Entrada LIKE ? OR Fecha_Salida LIKE ? OR Valor LIKE ? OR Forma_De_Pago LIKE ?");
            statement.setString(1, input + "%");
            statement.setString(2, input + "%");
            statement.setString(3, input + "%");
            statement.setString(4, input + "%");
            statement.setString(5, input + "%");
            try (statement) {
                statement.execute();

                final ResultSet resultSet = statement.getResultSet();

                try (resultSet) {
                    while (resultSet.next()) {
                        LocalDate d1 = CalcularReserva.convertToLocalDateViaSqlDate(resultSet.getDate("Fecha_Entrada"));
                        LocalDate d2 = CalcularReserva.convertToLocalDateViaSqlDate(resultSet.getDate("Fecha_Salida"));

                        Reserva fila = new Reserva(resultSet.getInt("id"),
                                d1,
                                d2,
                                resultSet.getDouble("Valor"),
                                resultSet.getString("Forma_De_Pago")
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

    public int eliminarReserva(int idReserva) {

        try {
            PreparedStatement statement;
            statement = con.prepareStatement("DELETE FROM reservas WHERE id = ?");

            try (statement) {
                statement.setInt(1, idReserva);
                statement.execute();

                int updateCount = statement.getUpdateCount();
                System.out.println("se elimina: " + updateCount);
                return updateCount;

            }

        } catch (SQLException e) {
            System.out.println("no se elimina");
            throw new RuntimeException(e);

        }

    }
    
    public int actualizarReserva(Reserva reserva) {
        
        int respuesta = 1;
        try {
            PreparedStatement statement;
            statement = con.prepareStatement("UPDATE reservas SET Fecha_Entrada = ?,Fecha_Salida = ?,Valor = ?,Forma_De_Pago = ? WHERE id = ?");
            
            try (statement) {
                 
                statement.setObject(1, reserva.getFechaEntrada());
                statement.setObject(2, reserva.getFechaSalida());
                statement.setDouble(3, reserva.getValor());
                statement.setString(4, reserva.getFormaDePago());   //?
                statement.setInt(5, reserva.getId());
                respuesta = statement.executeUpdate();
                                       

            }
        } catch (SQLException e) {
            System.out.println("error actualizando");
           // respuesta = 0;
            
        }
        
        return respuesta;
    }

}
