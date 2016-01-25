package com.bit.entities;

import java.util.List;
public class Conductor {
    public int id;
    public String rut;
    public String nombre;
    public String telefono;
    public int idCliente;
    public int idTruck;
    public int idSucursal;
    public List<Trucks> trucks;


    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdTruck() {
        return idTruck;
    }

    public void setIdTruck(int idTruck) {
        this.idTruck = idTruck;
    }

}
