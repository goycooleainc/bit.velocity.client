package com.bit.entities;

import java.util.List;

/**
 * Created by goycolea on 19/1/16.
 */
public class Eventos {

    private int id;
    private int idProductora;
    private String nombre;
    private String nombreProductora;
    private String fechaInicio;
    private String fechaTermino;
    private String precio;
    private String presupuesto;
    private String direccionEvento;
    private String coordenadas;
    private String telefono;
    private String email;
    private int oc;
    private int estado;
    private int capacidad;
    private int modeloNegocio;
    private int duracionContrato;
    private int controlAcceso;
    private String areaControl;
    private String detalle;
    private List<Beneficios> beneficios;
    private List<Invitados> invitados;

    public List<Invitados> getInvitados() {
        return invitados;
    }

    public void setInvitados(List<Invitados> invitados) {
        this.invitados = invitados;
    }

    public String getAreaControl() {
        return areaControl;
    }

    public void setAreaControl(String areaControl) {
        this.areaControl = areaControl;
    }

    public List<Beneficios> getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(List<Beneficios> beneficios) {
        this.beneficios = beneficios;
    }

    public int getControlAcceso() {
        return controlAcceso;
    }

    public void setControlAcceso(int controlAcceso) {
        this.controlAcceso = controlAcceso;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getNombreProductora() {
        return nombreProductora;
    }

    public void setNombreProductora(String nombreProductora) {
        this.nombreProductora = nombreProductora;
    }

    public int getIdProductora() {
        return idProductora;
    }

    public void setIdProductora(int idProductora) {
        this.idProductora = idProductora;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public String getDireccionEvento() {
        return direccionEvento;
    }

    public void setDireccionEvento(String direccionEvento) {
        this.direccionEvento = direccionEvento;
    }

    public int getDuracionContrato() {
        return duracionContrato;
    }

    public void setDuracionContrato(int duracionContrato) {
        this.duracionContrato = duracionContrato;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(String fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getModeloNegocio() {
        return modeloNegocio;
    }

    public void setModeloNegocio(int modeloNegocio) {
        this.modeloNegocio = modeloNegocio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getOc() {
        return oc;
    }

    public void setOc(int oc) {
        this.oc = oc;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(String presupuesto) {
        this.presupuesto = presupuesto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


}
