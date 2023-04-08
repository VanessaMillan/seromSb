package com.SeromSb.dajuva.app.demodajuva.modelo;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Venta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column
	private LocalDate fechaVent;

	@Column
	private double subTotal;

	@Column
	private String Estado;

	@Column(name="cod_venta")
	private String CodVenta;

	@OneToMany(mappedBy = "fkventa")
	List<Pedido> listPedido;

	@OneToMany(mappedBy = "fkventa")
	List<Devolucion> listDevoluciones;
	
	public Venta() {
	}

	public Venta(Integer id, LocalDate fechaVent, double subTotal, String estado, String codVenta) {
		this.id = id;
		this.fechaVent = fechaVent;
		this.subTotal = subTotal;
		Estado = estado;
		CodVenta = codVenta;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getFechaVent() {
		return fechaVent;
	}

	public void setFechaVent(LocalDate fechaVent) {
		this.fechaVent = fechaVent;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

	public String getCodVenta() {
		return CodVenta;
	}

	public void setCodVenta(String codVenta) {
		CodVenta = codVenta;
	}
}
