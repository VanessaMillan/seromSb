package com.SeromSb.dajuva.app.demodajuva.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.SeromSb.dajuva.app.demodajuva.modelo.Usuario;


public interface UsuarioFacade extends UserDetailsService{
	public List<Usuario> findALL();
	public Usuario findbyId (Integer id);
	public void create(Usuario Usu );
	public void update(Usuario Usu);
	public void delete(Usuario Usu);
	Usuario findByCorreo(String Correo); 
}


