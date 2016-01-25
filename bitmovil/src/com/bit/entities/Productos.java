package com.bit.entities;

/**
 * Created by goycolea on 17/11/15.
 */
public class Productos {

    private int id;
    private int idProductora;
    private int cantidad;
    private int categoria;
    private String nombreProductora;
    private String nombre;
    private String precio;
    private String detalle;
    private String codigo;
    private String moneda;
    private String fecha;

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }



    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProductora() {
        return idProductora;
    }

    public void setIdProductora(int idProductora) {
        this.idProductora = idProductora;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreProductora() {
        return nombreProductora;
    }

    public void setNombreProductora(String nombreProductora) {
        this.nombreProductora = nombreProductora;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }


}

