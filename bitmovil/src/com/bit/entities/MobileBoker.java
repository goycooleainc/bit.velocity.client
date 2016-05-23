package com.bit.entities;

import java.util.List;

public class MobileBoker {

    private int id;
    private int idUsuario;
    private EstadoCuenta estadoCuenta;
    private List<Avatar> avatares;
    private List<Transaccion> transacciones;
    private List<Productos> productos;
    private List<Cortesia> cortesiaCombo;
    private List<Cortesia> cortesiaEvento;
    private List<VentaDetalle> ventasDetalle;
    private List<Productos> productosBit;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public EstadoCuenta getEstadoCuenta() {
        return estadoCuenta;
    }

    public void setEstadoCuenta(EstadoCuenta estadoCuenta) {
        this.estadoCuenta = estadoCuenta;
    }

    public List<Avatar> getAvatares() {
        return avatares;
    }

    public void setAvatares(List<Avatar> avatares) {
        this.avatares = avatares;
    }

    public List<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(List<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }

    public List<Productos> getProductos() {
        return productos;
    }

    public void setProductos(List<Productos> productos) {
        this.productos = productos;
    }

    public List<Cortesia> getCortesiaCombo() {
        return cortesiaCombo;
    }

    public void setCortesiaCombo(List<Cortesia> cortesiaCombo) {
        this.cortesiaCombo = cortesiaCombo;
    }

    public List<Cortesia> getCortesiaEvento() {
        return cortesiaEvento;
    }

    public void setCortesiaEvento(List<Cortesia> cortesiaEvento) {
        this.cortesiaEvento = cortesiaEvento;
    }

    public List<VentaDetalle> getVentasDetalle() {
        return ventasDetalle;
    }

    public void setVentasDetalle(List<VentaDetalle> ventasDetalle) {
        this.ventasDetalle = ventasDetalle;
    }

    public List<Productos> getProductosBit() {
        return productosBit;
    }

    public void setProductosBit(List<Productos> productosBit) {
        this.productosBit = productosBit;
    }

}

