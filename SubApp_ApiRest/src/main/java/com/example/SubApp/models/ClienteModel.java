package com.example.SubApp.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;


import java.time.LocalDate;

@Entity
@Table(name="clientes")
public class ClienteModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nombre;

    @Column
    private String apellidos;

    @Column (name = "fecha_nacimiento")
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;

    @Column
    private String direccion;

    @Column
    private String telefono;

    @Column
    private String email;

    @Column
    private int numero_buceos;

    @Column
    private String certificaciones;

    @Column
    private String nivel;

    public ClienteModel(int id, String nombre, String apellidos, int numero_buceos, String certificaciones) {
       this.id = id;
       this.nombre = nombre;
       this.apellidos = apellidos;
       this.numero_buceos = numero_buceos;
       this.certificaciones = certificaciones;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumero_buceos() {
        return numero_buceos;
    }

    public void setNumero_buceos(int numero_buceos) {
        this.numero_buceos = numero_buceos;
    }

    public String getCertificaciones() {
        return certificaciones;
    }

    public void setCertificaciones(String certificaciones) {
        this.certificaciones = certificaciones;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public ClienteModel() {}
    
    
}
