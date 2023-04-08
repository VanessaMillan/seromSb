package com.SeromSb.dajuva.app.demodajuva.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Devolucion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id_devolucion;
	
	@ManyToOne
	@JoinColumn(name="fkventa_id")
	private Venta fkventa;
	
	@Column
	private String Descripcion;
	
	@Column
	private String Estado;

	public Devolucion() {
	}

	public Devolucion(Integer id_devolucion, Venta fkventa, String descripcion, String estado) {
		Id_devolucion = id_devolucion;
		this.fkventa = fkventa;
		Descripcion = descripcion;
		Estado = estado;
	}

	public Integer getId_devolucion() {
		return Id_devolucion;
	}

	public void setId_devolucion(Integer id_devolucion) {
		Id_devolucion = id_devolucion;
	}

	public Venta getFkventa() {
		return fkventa;
	}

	public void setFkventa(Venta fkventa) {
		this.fkventa = fkventa;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}
}
