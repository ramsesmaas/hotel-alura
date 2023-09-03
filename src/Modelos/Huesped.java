/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author maast
 */
public class Huesped {
     private Integer id;
     
     private String nombre;
     
     private String apellido;

    private LocalDate fechaNacimiento;

    private String nacionalidad;
    
    private int telefono;
    
    private Integer idResrva;

    public Huesped(String nombre, String apellido, LocalDate fechaNacimiento, String nacionalidad, int telefono, Integer idResrva) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
        this.idResrva = idResrva;
    }

    public Huesped(Integer id, String nombre, String apellido, LocalDate fechaNacimiento, String nacionalidad, int telefono, Integer idResrva) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
        this.idResrva = idResrva;
    }
    
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public Integer getIdResrva() {
        return idResrva;
    }

    public void setIdResrva(Integer idResrva) {
        this.idResrva = idResrva;
    }

    @Override
    public String toString() {
        return "Huesped{" + "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", fechaNacimiento=" + fechaNacimiento + ", nacionalidad=" + nacionalidad + ", telefono=" + telefono + ", idResrva=" + idResrva + '}';
    }
    
    
}
