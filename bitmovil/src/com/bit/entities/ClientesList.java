package com.bit.entities;

import java.util.List;

public class ClientesList {
	//List<Order> orders;
    private List<Clientes> clientes;
    private String error;
    
	public List<Clientes> getClientes() {
		return clientes;
	}
	public void setClientes(List<Clientes> clientes) {
		this.clientes = clientes;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
}
