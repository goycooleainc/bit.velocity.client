package com.bit.entities;

public class Email {

	private String email;
	private String descripcion;
	private int idVentaDetalle;


	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIdVentaDetalle() {
		return idVentaDetalle;
	}

	public void setIdVentaDetalle(int idVentaDetalle) {
		this.idVentaDetalle = idVentaDetalle;
	}
}
