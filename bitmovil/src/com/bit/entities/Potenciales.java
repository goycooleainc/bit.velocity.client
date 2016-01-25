package com.bit.entities;

public class Potenciales {

	private int id;
	private String problema;
	private String item;
	private String seccion;
	private String causa;
	private String solucion;
	private String consecuencia;
	private String horas_antes_de_falla;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProblema() {
		return problema;
	}
	public void setProblema(String problema) {
		this.problema = problema;
	}
	public String getCausa() {
		return causa;
	}
	public void setCausa(String causa) {
		this.causa = causa;
	}
	public String getSolucion() {
		return solucion;
	}
	public void setSolucion(String solucion) {
		this.solucion = solucion;
	}
	public String getConsecuencia() {
		return consecuencia;
	}
	public void setConsecuencia(String consecuencia) {
		this.consecuencia = consecuencia;
	}
	public String getHoras_antes_de_falla() {
		return horas_antes_de_falla;
	}
	public void setHoras_antes_de_falla(String horas_antes_de_falla) {
		this.horas_antes_de_falla = horas_antes_de_falla;
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
