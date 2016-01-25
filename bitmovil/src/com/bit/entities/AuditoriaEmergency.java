package com.bit.entities;

import java.util.List;

public class AuditoriaEmergency {

	private int id;
	private String rutEvaluador;
	private String nombreEvaluador;
	private String cargoEvaluador;
	private String nombreCliente;
	private String fecha;
	private String faena;
	private String ciudad;
	private String linea;
	private String pais;
	private String equipo;
	private String modelo;
	private String nro_serie;
	private String horometro;
	private String horometro_motor;
	private String horometro_percucion;
	private String horometro_motor_e1;
	private String horometro_motor_e2;
	private String horometro_motor_e3;
	private String horometro_motor_diesel;
	private String horometro_motor_b1;
	private String horometro_motor_b2;
	private String horometro_motor_b3;
	private String nro_serie_end;
	private String nro_serie_motor;
	private String time_start;
	private String time_end;
	private String rig_number;

	/// Hitos de la auditoria
	private List<HitosAuditorias> hitos;
	/// Hitos de la auditoria
	private List<HitosAuditorias> estado_funcionamiento;
	/// funcionamiento del equipo
	private List<String> estados_str;
	private List<Integer> estados_int;
	/// observaciones generales 
	private List<Observaciones> observaciones;
	/// problemas
	private List<Problema> problemas;
	/// repuestos
	private List<Repuesto> repuestos;
	/// potenciales
	private List<Potenciales> potenciales;
	/// registros
	private List<RegistrosParametros> registrosParametros;
	/// registros
	private List<RegistrosPresiones> registrosPresiones;
	/// registros
	private List<MedicionPresiones> medicionPresiones;
	/// registros
	private List<TestSistemaElectrico> testSistemaElectrico;
	//// video collection
	private List<String> videoCollection;
	/// datos cliente
	private String representante;
	/// cargo
	private String cargo;
	/// firma cliente
	private String firmaCliente;
	/// firma auditor
	private String firmaAuditor;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRutEvaluador() {
		return rutEvaluador;
	}
	public void setRutEvaluador(String rutEvaluador) {
		this.rutEvaluador = rutEvaluador;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getFaena() {
		return faena;
	}
	public void setFaena(String faena) {
		this.faena = faena;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getEquipo() {
		return equipo;
	}
	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getNro_serie() {
		return nro_serie;
	}
	public void setNro_serie(String nro_serie) {
		this.nro_serie = nro_serie;
	}
	public String getHorometro() {
		return horometro;
	}
	public void setHorometro(String horometro) {
		this.horometro = horometro;
	}
	public String getNro_serie_end() {
		return nro_serie_end;
	}
	public void setNro_serie_end(String nro_serie_end) {
		this.nro_serie_end = nro_serie_end;
	}
	public String getNro_serie_motor() {
		return nro_serie_motor;
	}
	public void setNro_serie_motor(String nro_serie_motor) {
		this.nro_serie_motor = nro_serie_motor;
	}
	public List<HitosAuditorias> getHitos() {
		return hitos;
	}
	public void setHitos(List<HitosAuditorias> hitos) {
		this.hitos = hitos;
	}
	public List<String> getEstados_str() {
		return estados_str;
	}
	public void setEstados_str(List<String> estados_str) {
		this.estados_str = estados_str;
	}
	public List<Integer> getEstados_int() {
		return estados_int;
	}
	public void setEstados_int(List<Integer> estados_int) {
		this.estados_int = estados_int;
	}

	public List<Problema> getProblemas() {
		return problemas;
	}
	public void setProblemas(List<Problema> problemas) {
		this.problemas = problemas;
	}
	public List<Repuesto> getRepuestos() {
		return repuestos;
	}
	public void setRepuestos(List<Repuesto> repuestos) {
		this.repuestos = repuestos;
	}
	public List<Potenciales> getPotenciales() {
		return potenciales;
	}
	public void setPotenciales(List<Potenciales> potenciales) {
		this.potenciales = potenciales;
	}
	public String getRepresentante() {
		return representante;
	}
	public void setRepresentante(String representante) {
		this.representante = representante;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getFirmaCliente() {
		return firmaCliente;
	}
	public void setFirmaCliente(String firmaCliente) {
		this.firmaCliente = firmaCliente;
	}
	public String getFirmaAuditor() {
		return firmaAuditor;
	}
	public void setFirmaAuditor(String firmaAuditor) {
		this.firmaAuditor = firmaAuditor;
	}

	public String getTime_start() {
		return time_start;
	}
	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}
	public String getTime_end() {
		return time_end;
	}
	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}
	public List<Observaciones> getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(List<Observaciones> observaciones) {
		this.observaciones = observaciones;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public String getHorometro_motor() {
		return horometro_motor;
	}
	public void setHorometro_motor(String horometro_motor) {
		this.horometro_motor = horometro_motor;
	}
	public String getHorometro_percucion() {
		return horometro_percucion;
	}
	public void setHorometro_percucion(String horometro_percucion) {
		this.horometro_percucion = horometro_percucion;
	}
	public String getRig_number() {
		return rig_number;
	}
	public void setRig_number(String rig_number) {
		this.rig_number = rig_number;
	}
	public String getHorometro_motor_e1() {
		return horometro_motor_e1;
	}
	public void setHorometro_motor_e1(String horometro_motor_e1) {
		this.horometro_motor_e1 = horometro_motor_e1;
	}
	public String getHorometro_motor_e2() {
		return horometro_motor_e2;
	}
	public void setHorometro_motor_e2(String horometro_motor_e2) {
		this.horometro_motor_e2 = horometro_motor_e2;
	}
	public String getHorometro_motor_e3() {
		return horometro_motor_e3;
	}
	public void setHorometro_motor_e3(String horometro_motor_e3) {
		this.horometro_motor_e3 = horometro_motor_e3;
	}
	public String getHorometro_motor_diesel() {
		return horometro_motor_diesel;
	}
	public void setHorometro_motor_diesel(String horometro_motor_diesel) {
		this.horometro_motor_diesel = horometro_motor_diesel;
	}
	public String getHorometro_motor_b1() {
		return horometro_motor_b1;
	}
	public void setHorometro_motor_b1(String horometro_motor_b1) {
		this.horometro_motor_b1 = horometro_motor_b1;
	}
	public String getHorometro_motor_b2() {
		return horometro_motor_b2;
	}
	public void setHorometro_motor_b2(String horometro_motor_b2) {
		this.horometro_motor_b2 = horometro_motor_b2;
	}
	public String getHorometro_motor_b3() {
		return horometro_motor_b3;
	}
	public void setHorometro_motor_b3(String horometro_motor_b3) {
		this.horometro_motor_b3 = horometro_motor_b3;
	}
	public String getNombreEvaluador() {
		return nombreEvaluador;
	}
	public void setNombreEvaluador(String nombreEvaluador) {
		this.nombreEvaluador = nombreEvaluador;
	}
	public String getCargoEvaluador() {
		return cargoEvaluador;
	}
	public void setCargoEvaluador(String cargoEvaluador) {
		this.cargoEvaluador = cargoEvaluador;
	}
	public String getLinea() {
		return linea;
	}
	public void setLinea(String linea) {
		this.linea = linea;
	}
	public List<HitosAuditorias> getEstado_funcionamiento() {
		return estado_funcionamiento;
	}
	public void setEstado_funcionamiento(List<HitosAuditorias> estado_funcionamiento) {
		this.estado_funcionamiento = estado_funcionamiento;
	}
	public List<RegistrosParametros> getRegistrosParametros() {
		return registrosParametros;
	}
	public void setRegistrosParametros(List<RegistrosParametros> registrosParametros) {
		this.registrosParametros = registrosParametros;
	}
	public List<RegistrosPresiones> getRegistrosPresiones() {
		return registrosPresiones;
	}
	public void setRegistrosPresiones(List<RegistrosPresiones> registrosPresiones) {
		this.registrosPresiones = registrosPresiones;
	}
	public List<MedicionPresiones> getMedicionPresiones() {
		return medicionPresiones;
	}
	public void setMedicionPresiones(List<MedicionPresiones> medicionPresiones) {
		this.medicionPresiones = medicionPresiones;
	}
	public List<TestSistemaElectrico> getTestSistemaElectrico() {
		return testSistemaElectrico;
	}
	public void setTestSistemaElectrico(List<TestSistemaElectrico> testSistemaElectrico) {
		this.testSistemaElectrico = testSistemaElectrico;
	}
	public List<String> getVideoCollection() {
		return videoCollection;
	}
	public void setVideoCollection(List<String> videoCollection) {
		this.videoCollection = videoCollection;
	}
}
