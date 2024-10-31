package com.example.SubApp.models;

import jakarta.persistence.*;

@Entity
@Table(name = "actividades")
public class ActividadesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "salida_id")
    private int numeroSalida;

    @Column
    private int cliente_id;

    @Column
    private int botellas_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumeroSalida() {
        return numeroSalida;
    }

    public void setNumeroSalida(int numeroSalida) {
        this.numeroSalida = numeroSalida;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public int getBotellas_id() {
        return botellas_id;
    }

    public void setBotellas_id(int botellas_id) {
        this.botellas_id = botellas_id;
    }

    public ActividadesModel() {
    }
}
