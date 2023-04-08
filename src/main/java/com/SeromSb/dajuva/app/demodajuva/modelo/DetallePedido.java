package com.SeromSb.dajuva.app.demodajuva.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class DetallePedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id_Dpedido;
	
	@Column
	private Integer Cant_productos;
	
	@Column
	private String Estado_pedido;
	
	@Column
	private String Estado;
	
	@ManyToOne
	Pedido 	fkPedido;
	
	@ManyToOne
	Producto fkProducto;
	
	//Getters and Setters
	public Integer getId_Dpedido() {
		return Id_Dpedido;
	}

	public void setId_Dpedido(Integer id_Dpedido) {
		Id_Dpedido = id_Dpedido;
	}

	public Integer getCant_productos() {
		return Cant_productos;
	}

	public void setCant_productos(Integer cant_productos) {
		Cant_productos = cant_productos;
	}

	public String getEstado_pedido() {
		return Estado_pedido;
	}

	public void setEstado_pedido(String estado_pedido) {
		Estado_pedido = estado_pedido;
	}
	
	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

	public Pedido getFkPedido() {
		return fkPedido;
	}

	public void setFkPedido(Pedido fkPedido) {
		this.fkPedido = fkPedido;
	}

	public Producto getFkProducto() {
		return fkProducto;
	}

	public void setFkProducto(Producto fkProducto) {
		this.fkProducto = fkProducto;
	}


	
	
}
