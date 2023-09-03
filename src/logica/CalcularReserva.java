/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.time.LocalDate;
import java.time.ZoneId;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.Date;

/**
 *
 * @author maast
 */
public class CalcularReserva {
    
    
    
public static double calcularReserva(LocalDate inicio, LocalDate fin){
     long dias = DAYS.between(inicio, fin);
     System.out.println("dias: "+ dias);
     double tarifa = dias * 10;
     
     return tarifa;
}

public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
   
    System.out.println(dateToConvert.toInstant()
      .atZone(ZoneId.systemDefault())
      .toLocalDate());
    
    return dateToConvert.toInstant()
      .atZone(ZoneId.systemDefault())
      .toLocalDate();
}

public static LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
   // System.out.println("convertido: " + new java.sql.Date(dateToConvert.getTime()).toLocalDate());
    return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
}

    
}
