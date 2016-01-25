package com.bit.entities;

import java.util.List;

public class ComboData {
	List<BasicBean> servicios;
	List<BasicBean> andenes;
	List<BasicBean> mecanicos;
    List<BasicBean> ayudantes;
    List<BasicBean> tiposRetraso;
	
	public List<BasicBean> getServicios() {
		return servicios;
	}
	public void setServicios(List<BasicBean> servicios) {
		this.servicios = servicios;
	}
	public List<BasicBean> getAndenes() {
		return andenes;
	}
	public void setAndenes(List<BasicBean> andenes) {
		this.andenes = andenes;
	}
	public List<BasicBean> getMecanicos() {
		return mecanicos;
	}
	public void setMecanicos(List<BasicBean> mecanicos) {
		this.mecanicos = mecanicos;
	}
    public List<BasicBean> getAyudantes() {
        return ayudantes;
    }
    public void setAyudantes(List<BasicBean> ayudantes) {
        this.ayudantes = ayudantes;
    }
    public List<BasicBean> getTiposRetraso() {
        return tiposRetraso;
    }
    public void setTiposRetraso(List<BasicBean> tiposRetraso) {
        this.tiposRetraso = tiposRetraso;
    }
}
