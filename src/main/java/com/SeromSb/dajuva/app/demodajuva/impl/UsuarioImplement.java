package com.SeromSb.dajuva.app.demodajuva.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.SeromSb.dajuva.app.demodajuva.modelo.Rol;
import com.SeromSb.dajuva.app.demodajuva.modelo.Usuario;
import com.SeromSb.dajuva.app.demodajuva.repositorio.IUsuarioRepository;
import com.SeromSb.dajuva.app.demodajuva.service.UsuarioFacade;

@Service
public class UsuarioImplement implements UsuarioFacade {

	@Autowired
	IUsuarioRepository iUsuarioRepository;

	@Override
	public List<Usuario> findALL() {
		return this.iUsuarioRepository.findAll();
	}

	@SuppressWarnings("deprecation")
	@Override
	public Usuario findbyId(Integer id) {
		return this.iUsuarioRepository.getById(id);
	}

	@Override
	public void create(Usuario usu) {
		this.iUsuarioRepository.save(usu);
	}

	@Override
	public void update(Usuario usu) {
		this.iUsuarioRepository.save(usu);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void delete(Usuario usu) {
		Usuario User = this.iUsuarioRepository.getById(usu.getId());
		this.iUsuarioRepository.delete(User);
	}
	
	@Override
	public Usuario findByCorreo(String Correo) {
		return this.iUsuarioRepository.findByCorreo(Correo);
	}

	public static Usuario findById(int parseInt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = iUsuarioRepository.findByCorreo(username);
		if (usuario == null) {
			throw new UsernameNotFoundException("Usuario o password inválidos");
		}
		return new User(usuario.getCorreo(), usuario.getClave(), mapearAutoridadesRoles(usuario.getFkIdRol()));
	}
	
	private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Rol rol){
		
		SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(rol.getRol_nombre());
		
		return Collections.singleton(grantedAuthority);
	}
}
