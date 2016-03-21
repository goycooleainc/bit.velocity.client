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
    private String sector1;
    private String sector2;
    private String sector3;
    private String sector4;
    private String sector5;
    private String sector6;
    private String sector7;
    private String sector8;
    private String sector9;
    private String sector10;

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

    public String getSector1() {
        return sector1;
    }

    public void setSector1(String sector1) {
        this.sector1 = sector1;
    }

    public String getSector2() {
        return sector2;
    }

    public void setSector2(String sector2) {
        this.sector2 = sector2;
    }

    public String getSector3() {
        return sector3;
    }

    public void setSector3(String sector3) {
        this.sector3 = sector3;
    }

    public String getSector4() {
        return sector4;
    }

    public void setSector4(String sector4) {
        this.sector4 = sector4;
    }

    public String getSector5() {
        return sector5;
    }

    public void setSector5(String sector5) {
        this.sector5 = sector5;
    }

    public String getSector6() {
        return sector6;
    }

    public void setSector6(String sector6) {
        this.sector6 = sector6;
    }

    public String getSector7() {
        return sector7;
    }

    public void setSector7(String sector7) {
        this.sector7 = sector7;
    }

    public String getSector8() {
        return sector8;
    }

    public void setSector8(String sector8) {
        this.sector8 = sector8;
    }

    public String getSector9() {
        return sector9;
    }

    public void setSector9(String sector9) {
        this.sector9 = sector9;
    }

    public String getSector10() {
        return sector10;
    }

    public void setSector10(String sector10) {
        this.sector10 = sector10;
    }

}
