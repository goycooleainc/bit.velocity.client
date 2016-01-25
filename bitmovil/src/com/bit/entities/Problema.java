package com.bit.entities;

public class Problema {

	private int id;
	private String hecho;
	private String observacion;
	private String item;
	private String seccion;
	
	
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getHecho() {
		return hecho;
	}
	public void setHecho(String hecho) {
		this.hecho = hecho;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getSeccion() {
		return seccion;
	}
	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}
}
