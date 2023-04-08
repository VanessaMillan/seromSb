package com.SeromSb.dajuva.app.demodajuva.modelo;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String usu_nombre;

	@Column
	private String usu_apellido;

	@Column
	private String correo;

	@Column
	private String direccion;

	@Column
	private String ciudad;

	@Column
	private Integer telefono;

	@Column
	private String clave;

	@Column
	private String tipo_Doc;

	@Column
	private Integer num_Doc;

	@Column
	private Integer edad;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column
	private LocalDate fecha_Nac;
	
	@Column
	private String estado;
	 
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="fkidrol")
	private Rol fkIdRol;

	public Usuario() {
	}
	
	public Usuario(Integer id, String usu_nombre, String usu_apellido, String correo, String direccion, String ciudad,
			Integer telefono, String clave, String tipo_Doc, Integer num_Doc, Integer edad, LocalDate fecha_Nac,
			String estado, Rol fkIdRol) {
		this.id = id;
		this.usu_nombre = usu_nombre;
		this.usu_apellido = usu_apellido;
		this.correo = correo;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.telefono = telefono;
		this.clave = clave;
		this.tipo_Doc = tipo_Doc;
		this.num_Doc = num_Doc;
		this.edad = edad;
		this.fecha_Nac = fecha_Nac;
		this.estado = estado;
		this.fkIdRol = fkIdRol;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsu_nombre() {
		return usu_nombre;
	}

	public void setUsu_nombre(String usu_nombre) {
		this.usu_nombre = usu_nombre;
	}

	public String getUsu_apellido() {
		return usu_apellido;
	}

	public void setUsu_apellido(String usu_apellido) {
		this.usu_apellido = usu_apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getTipo_Doc() {
		return tipo_Doc;
	}

	public void setTipo_Doc(String tipo_Doc) {
		this.tipo_Doc = tipo_Doc;
	}

	public Integer getNum_Doc() {
		return num_Doc;
	}

	public void setNum_Doc(Integer num_Doc) {
		this.num_Doc = num_Doc;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public LocalDate getFecha_Nac() {
		return fecha_Nac;
	}

	public void setFecha_Nac(LocalDate fecha_Nac) {
		this.fecha_Nac = fecha_Nac;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Rol getFkIdRol() {
		return fkIdRol;
	}

	public void setFkIdRol(Rol fkIdRol) {
		this.fkIdRol = fkIdRol;
	}
}
