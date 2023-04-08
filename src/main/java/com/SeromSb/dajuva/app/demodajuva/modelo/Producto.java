package com.SeromSb.dajuva.app.demodajuva.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; 
	
	@Column
	private String prodNombre; 
	
	@Column
	private double prodPrecio; 

	@Column
	private String prodTalla; 
	
	@Column
	private String prodColor; 
	
	@Column
	private String prodMarca;
	
	@Column
	private String Estado;
	
	@ManyToOne
	Categoria fkCategoria;
	
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(name = "Inventario	",
		joinColumns = @JoinColumn(name = "fk_IdEntradas",nullable = false),
		inverseJoinColumns = @JoinColumn(name = "fk_IdProductos",nullable = false)
	   )
		private List<Entrada> ListEntradas;
	
	@OneToMany(mappedBy="fkProducto")
	List<DetallePedido>listDpedido;
	
	//Getters and Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProdNombre() {
		return prodNombre;
	}

	public void setProdNombre(String prodNombre) {
		this.prodNombre = prodNombre;
	}

	public double getProdPrecio() {
		return prodPrecio;
	}

	public void setProdPrecio(double prodPrecio) {
		this.prodPrecio = prodPrecio;
	}

	public String getProdTalla() {
		return prodTalla;
	}

	public void setProdTalla(String prodTalla) {
		this.prodTalla = prodTalla;
	}

	public String getProdColor() {
		return prodColor;
	}

	public void setProdColor(String prodColor) {
		this.prodColor = prodColor;
	}

	public String getProdMarca() {
		return prodMarca;
	}

	public void setProdMarca(String prodMarca) {
		this.prodMarca = prodMarca;
	}
	
	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

	public Categoria getFkCategoria() {
		return fkCategoria;
	}

	public void setFkCategoria(Categoria fkCategoria) {
		this.fkCategoria = fkCategoria;
	}

	public List<Entrada> getListEntradas() {
		return ListEntradas;
	}

	public void setListEntradas(List<Entrada> listEntradas) {
		ListEntradas = listEntradas;
	}

	public List<DetallePedido> getListDpedido() {
		return listDpedido;
	}

	public void setListDpedido(List<DetallePedido> listDpedido) {
		this.listDpedido = listDpedido;
	}
	
	
}
