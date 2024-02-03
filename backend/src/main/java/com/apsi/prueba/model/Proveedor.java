package com.apsi.prueba.model;

public class Proveedor {
    private String nombre;
    private String razonSocial;
    private String direccion;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Proveedor(String nombre, String razonSocial, String direccion) {
		super();
		this.nombre = nombre;
		this.razonSocial = razonSocial;
		this.direccion = direccion;
	}
	public Proveedor() {
		super();
		// TODO Auto-generated constructor stub
	}


}