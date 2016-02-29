package com.bit.entities;

/**
 * Created by goycolea on 23/11/15.
 */
public class Venta {

    public int id;
    public int idTransaccion;
    public int idProductora;
    public int idEvento;
    public int idUser;
    public String fecha;
    public String total;
    public String resultado;
    public String respuestaAdquiriente;
    public String codigoVerificacion;
    public String codigoPsp;
    public String puntosRiesgo;
    public String avatar;
    public int cantidad;
    public int cantidadParaEnviar;

    public int getCantidadParaEnviar() {
        return cantidadParaEnviar;
    }

    public void setCantidadParaEnviar(int cantidadParaEnviar) {
        this.cantidadParaEnviar = cantidadParaEnviar;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCodigoPsp() {
        return codigoPsp;
    }

    public void setCodigoPsp(String codigoPsp) {
        this.codigoPsp = codigoPsp;
    }

    public String getCodigoVerificacion() {
        return codigoVerificacion;
    }

    public void setCodigoVerificacion(String codigoVerificacion) {
        this.codigoVerificacion = codigoVerificacion;
    }

    public String getPuntosRiesgo() {
        return puntosRiesgo;
    }

    public void setPuntosRiesgo(String puntosRiesgo) {
        this.puntosRiesgo = puntosRiesgo;
    }

    public String getRespuestaAdquiriente() {
        return respuestaAdquiriente;
    }

    public void setRespuestaAdquiriente(String respuestaAdquiriente) {
        this.respuestaAdquiriente = respuestaAdquiriente;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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

    public int getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

}
