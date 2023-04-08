package com.SeromSb.dajuva.app.demodajuva.modelo;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id_pedido;
	
	@Column
	private String Cod_pedido;
	
	@Column
	private String Estado;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column
	private LocalDate Ped_FechaPed;	
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column
	private LocalDate Ped_FechaEnt;
	
	@ManyToOne
	Usuario Fk_IdUsu;
	
	@ManyToOne
	Venta fkventa; 
	
	@OneToMany(mappedBy="fkPedido")
	List<DetallePedido>listDpedido;
	
	public Venta getFkventa() {
		return fkventa;
	}

	public void setFkventa(Venta fkventa) {
		this.fkventa = fkventa;
	}


	public Integer getId_pedido() {
		return Id_pedido;
	}

	public void setId_pedido(Integer id_pedido) {
		Id_pedido = id_pedido;
	}
	
	public String getCod_pedido() {
		return Cod_pedido;
	}

	public void setCod_pedido(String cod_pedido) {
		Cod_pedido = cod_pedido;
	}
	
	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

	public LocalDate getPed_FechaPed() {
		return Ped_FechaPed;
	}

	public void setPed_FechaPed(LocalDate ped_FechaPed) {
		Ped_FechaPed = ped_FechaPed;
	}

	public LocalDate getPed_FechaEnt() {
		return Ped_FechaEnt;
	}

	public void setPed_FechaEnt(LocalDate ped_FechaEnt) {
		Ped_FechaEnt = ped_FechaEnt;
	}



	public Usuario getFk_IdUsu() {
		return Fk_IdUsu;
	}

	public void setFk_IdUsu(Usuario fk_IdUsu) {
		Fk_IdUsu = fk_IdUsu;
	}


	public List<DetallePedido> getListDpedido() {
		return listDpedido;
	}

	public void setListDpedido(List<DetallePedido> listDpedido) {
		this.listDpedido = listDpedido;
	}
	
	
}
