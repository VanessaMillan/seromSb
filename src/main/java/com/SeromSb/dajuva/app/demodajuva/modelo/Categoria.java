package com.SeromSb.dajuva.app.demodajuva.modelo;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity

public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer IdCategoria;
	
	@Column
	private String CatProducto;
	
	@Column
	private String Estado;

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

	@OneToMany(mappedBy="fkCategoria")
	List<Producto> listProducto;
	
	//Getters and Setters
	public Integer getIdCategoria() {
		return IdCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		IdCategoria = idCategoria;
	}

	public String getCatProducto() {
		return CatProducto;
	}

	public void setCatProducto(String catProducto) {
		CatProducto = catProducto;
	}

	public List<Producto> getListProducto() {
		return listProducto;
	}

	public void setListProducto(List<Producto> listProducto) {
		this.listProducto = listProducto;
	}
		
}
