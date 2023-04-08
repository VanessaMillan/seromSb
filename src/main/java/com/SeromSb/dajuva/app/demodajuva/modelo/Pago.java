package com.SeromSb.dajuva.app.demodajuva.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Pago {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; 
	
	@Column
	private String metPago; 
	
	@Column
	private double iva; 
	
	@Column
	private double valorTotal; 
	
	@Column
	private String Estado;
	
	@ManyToOne
	Venta pagVenta;
	
	//Getters and setters//

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMetPago() {
		return metPago;
	}

	public void setMetPago(String metPago) {
		this.metPago = metPago;
	}

	public double getIva() {
		return iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Venta getPagVenta() {
		return pagVenta;
	}

	public void setPagVenta(Venta pagVenta) {
		this.pagVenta = pagVenta;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	} 
	
	
}
