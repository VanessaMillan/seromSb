package com.SeromSb.dajuva.app.demodajuva.service;

import java.util.List;

import com.SeromSb.dajuva.app.demodajuva.modelo.Rol;

public interface RolFacade {
	public List<Rol> findAll();
	public Rol findbyId (Integer id);
	public void create(Rol Rol );
	public void update(Rol Rol);
	public void delete(int iD);
}
