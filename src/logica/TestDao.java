/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import Dao.HuespedDao;
import Dao.ReservaDao;
import Modelos.FormaDePago;
import Modelos.Huesped;
import Modelos.Reserva;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.List;

/**
 *
 * @author maast
 */
public class TestDao {
    public static void main(String[] args) throws SQLException {
        
      
        
        Conexion con = new Conexion();
       
        
        
        
  /*     
         ReservaDao r1 = new ReservaDao(con.recuperaConexion());
        FormaDePago f1 = FormaDePago.DINERO_EN_EFECTIVO;
       
       // Creo los LocalDate
        LocalDate inicio = LocalDate.of(2019, 1, 1); // 1 de enero 2019
        LocalDate fin = LocalDate.of(2020, 1, 1);// 1 de enero 2020
       
       
        Reserva rn = new Reserva(inicio,fin,TestDao.dias(inicio, fin), "DINERO_EN_EFECTIVO");
         //System.out.println(rn.getFechaEntrada());
         System.out.println(rn.toString());
      //  r1.guardar(rn);
*/
  
 /* LocalDate nacimientto = LocalDate.of(2000, 8, 20); // 1 de enero 2019 //a, mes, dia
  Huesped h1 = new Huesped("Ramses","Maas",nacimientto,"",12345679,9);
  HuespedDao hd = new HuespedDao(con.recuperaConexion());
  int id = hd.guardar(h1);
        System.out.println("id"+ id);
  */
  /*
  ReservaDao rd = new ReservaDao(con.recuperaConexion());
  List<Reserva> r1 = rd.listarReservasPorBusqueda("160");
  
   for(Reserva reserva : r1)
{
    //imprimimos el objeto pivote
    System.out.println(r1);
}*/
  
/*  HuespedDao h1 = new HuespedDao(con.recuperaConexion());

  List<Huesped> h2 = h1.listarHuespedesPorBusqueda("mexicano");
     for(Huesped huesped : h2)
{
    //imprimimos el objeto pivote
    System.out.println(h2);
}
  */

LocalDate fe = LocalDate.of(2000, 7, 20); // 1 de enero 2019 //a, mes, dia
LocalDate fs = LocalDate.of(2000, 8, 23); // 1 de enero 2019 //a, mes, dia
ReservaDao rd = new ReservaDao(con.recuperaConexion());
Reserva r1 = new Reserva(9,fe,fs,350,"cado");
int resultado = rd.actualizarReserva(r1);
System.out.println("ase actualizo"+resultado +", " + r1.getId()+ r1);
 
/*
LocalDate fe = LocalDate.of(2000, 7, 28); // 1 de enero 2019 //a, mes, dia

HuespedDao HD = new HuespedDao(con.recuperaConexion());
Huesped h1 = new Huesped(5,"Ramses","Maas",fe,"Mexicano",39423455,9);        
 HD.actualizarHuesped(h1);
*/
}


public static double dias(LocalDate inicio, LocalDate fin){
     long dias = DAYS.between(inicio, fin);
     System.out.println("dias: " + dias);
     double tarifa = dias * 10;
     
     return tarifa;
}

}


