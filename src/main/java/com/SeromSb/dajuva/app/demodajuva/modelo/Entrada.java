package com.SeromSb.dajuva.app.demodajuva.modelo;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Entrada {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id_Entradas;
	
	@Column
	private String Ent_NomProd;
	
	@Column
	private Integer Ent_CantidadProd;
	
	@Column
	private String Estado;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column 
	private LocalDate Ent_Fecha;
	
	@ManyToMany(mappedBy="ListEntradas", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private List<Producto> 	listProducto;
	
	
	//Getters and Setters
	public Integer getId_Entradas() {
		return Id_Entradas;
	}

	public void setId_Entradas(Integer id_Entradas) {
		Id_Entradas = id_Entradas;
	}

	public String getEnt_NomProd() {
		return Ent_NomProd;
	}

	public void setEnt_NomProd(String ent_NomProd) {
		Ent_NomProd = ent_NomProd;
	}

	public Integer getEnt_CantidadProd() {
		return Ent_CantidadProd;
	}

	public void setEnt_CantidadProd(Integer ent_CantidadProd) {
		Ent_CantidadProd = ent_CantidadProd;
	}
	
	public LocalDate getEnt_Fecha() {
		return Ent_Fecha;
	}

	public void setEnt_Fecha(LocalDate ent_Fecha) {
		Ent_Fecha = ent_Fecha;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

	public List<Producto> getListProducto() {
		return listProducto;
	}

	public void setListProducto(List<Producto> listProducto) {
		this.listProducto = listProducto;
	}

	
	
	
}
