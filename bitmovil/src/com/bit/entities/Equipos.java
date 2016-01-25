package com.bit.entities;

public class Equipos {

	private int id;
	private int id_cliente;
	private int modelo;
	private String nro_serie;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}
	public int getModelo() {
		return modelo;
	}
	public void setModelo(int modelo) {
		this.modelo = modelo;
	}
	public String getNro_serie() {
		return nro_serie;
	}
	public void setNro_serie(String nro_serie) {
		this.nro_serie = nro_serie;
	}
}
