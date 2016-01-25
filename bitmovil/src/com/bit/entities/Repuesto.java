package com.bit.entities;

public class Repuesto {

	private int id;
	private String seccion;
	private String seccion_item;
	private String item;
	private String nro_parte;
	private String descripcion;
	private String cantidad;
	private String precio_unitario;
	private String extension;
	private String terminos_vta;
	
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
	public String getNro_parte() {
		return nro_parte;
	}
	public void setNro_parte(String nro_parte) {
		this.nro_parte = nro_parte;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getCantidad() {
		return cantidad;
	}
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
	public String getPrecio_unitario() {
		return precio_unitario;
	}
	public void setPrecio_unitario(String precio_unitario) {
		this.precio_unitario = precio_unitario;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String getTerminos_vta() {
		return terminos_vta;
	}
	public void setTerminos_vta(String terminos_vta) {
		this.terminos_vta = terminos_vta;
	}
	public String getSeccion_item() {
		return seccion_item;
	}
	public void setSeccion_item(String seccion_item) {
		this.seccion_item = seccion_item;
	}
	public String getSeccion() {
		return seccion;
	}
	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}
	
}
