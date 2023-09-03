/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maast
 */
public class Reserva {

    private Integer id;

    private LocalDate FechaEntrada;

    private LocalDate FechaSalida;

    private double Valor;
    
    private String FormaDePago;
    
   // private List<Huesped> huespedes = new ArrayList<>();

    public Reserva(LocalDate FechaEntrada, LocalDate FechaSalida, Double Valor, String FormaDePago) {
        this.FechaEntrada = FechaEntrada;
        this.FechaSalida = FechaSalida;
        this.Valor = Valor;
        this.FormaDePago = FormaDePago;
    }

    public Reserva(Integer id, LocalDate FechaEntrada, LocalDate FechaSalida, double Valor, String FormaDePago) {
        this.id = id;
        this.FechaEntrada = FechaEntrada;
        this.FechaSalida = FechaSalida;
        this.Valor = Valor;
        this.FormaDePago = FormaDePago;
    }

    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFechaEntrada() {
        return FechaEntrada;
    }

    public void setFechaEntrada(LocalDate FechaEntrada) {
        this.FechaEntrada = FechaEntrada;
    }

    public LocalDate getFechaSalida() {
        return FechaSalida;
    }

    public void setFechaSalida(LocalDate FechaSalida) {
        this.FechaSalida = FechaSalida;
    }

    public Double getValor() {
        return Valor;
    }

    public void setValor(Double Valor) {
        this.Valor = Valor;
    }

    public String getFormaDePago() {
        return FormaDePago;
    }

    public void setFormaDePago(String FormaDePago) {
        this.FormaDePago = FormaDePago;
    }

    @Override
    public String toString() {
        return "Reserva{" + "FechaEntrada=" + FechaEntrada + ", FechaSalida=" + FechaSalida + ", Valor=" + Valor + ", FormaDePago=" + FormaDePago + '}';
    }

    

    
    
}
