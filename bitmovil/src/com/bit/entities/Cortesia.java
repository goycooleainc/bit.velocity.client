package com.bit.entities;

import java.util.List;
import java.util.Map;

/**
 * Created by gfurlano on 3/30/16.
 */
public class Cortesia {

    private int id;
    private int idProductora;
    private int idProductoCombo;
    private int idUsuario;
    private int idEvento;
    private String idAvatar;
    private int cantidad;
    private String nombreCombo;
    private String nombreProductora;
    private String nombreEvento;
    private String fechaInicio;
    private List<String> listP;
    private Map<String, String> productoCantidad;

    public String getFecha() {
        return fechaInicio;
    }

    public void setFecha(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getIdAvatar() {
        return idAvatar;
    }

    public void setIdAvatar(String idAvatar) {
        this.idAvatar = idAvatar;
    }

    public List<String> getListP() {
        return listP;
    }

    public void setListP(List<String> listP) {
        this.listP = listP;
    }

    public Map<String, String> getProductoCantidad() {
        return productoCantidad;
    }

    public void setProductoCantidad(Map<String, String> productoCantidad) {
        this.productoCantidad = productoCantidad;
    }

    public String getNombreCombo() {
        return nombreCombo;
    }

    public void setNombreCombo(String nombreCombo) {
        this.nombreCombo = nombreCombo;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
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

    public int getIdProductoCombo() {
        return idProductoCombo;
    }

    public void setIdProductoCombo(int idProductoCombo) {
        this.idProductoCombo = idProductoCombo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombreProductora() {
        return nombreProductora;
    }

    public void setNombreProductora(String nombreProductora) {
        this.nombreProductora = nombreProductora;
    }

}
