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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "rol")
public class Rol {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_rol;

	private String rol_nombre;
	
	@Column
	private String estado;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "roles_has_permisos", 
	joinColumns = @JoinColumn(name = "fk_IdRol"),
	inverseJoinColumns = @JoinColumn(name = "id_permiso_fk"))
	private List<Permiso> listPermisos;
	
	@OneToMany(mappedBy="fkIdRol")
	private List<Usuario> listUsuario;

	public Rol() {
	}
	
	public Rol(Integer id_rol, String rol_nombre, String estado) {
		this.id_rol = id_rol;
		this.rol_nombre = rol_nombre;
		this.estado = estado;
	}

	public Integer getId_rol() {
		return id_rol;
	}

	public void setId_rol(Integer id_rol) {
		this.id_rol = id_rol;
	}

	public String getRol_nombre() {
		return rol_nombre;
	}

	public void setRol_nombre(String rol_nombre) {
		this.rol_nombre = rol_nombre;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}
	
	

	