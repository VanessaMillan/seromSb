package com.SeromSb.dajuva.app.demodajuva.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Permiso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer IdPermiso;
	
	@Column
	private String Per_Desc;
	
	@Column
	private String Estado;
	
	@ManyToMany(mappedBy="listPermisos", cascade = CascadeType.ALL)
	private List<Rol> listRol;
	
	//Getters and Setters
	public Integer getIdPermiso() {
		return IdPermiso;
	}

	public void setIdPermiso(Integer idPermiso) {
		IdPermiso = idPermiso;
	}

	public String getPer_Desc() {
		return Per_Desc;
	}

	public void setPer_Desc(String per_Desc) {
		Per_Desc = per_Desc;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

	public List<Rol> getListRol() {
		return listRol;
	}

	public void setListRol(List<Rol> listRol) {
		this.listRol = listRol;
	}
	
}
