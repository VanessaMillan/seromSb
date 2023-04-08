package com.SeromSb.dajuva.app.demodajuva.service;

import java.util.List;

import com.SeromSb.dajuva.app.demodajuva.modelo.Permiso;


public interface PermisoFacade {
	public List<Permiso> findALL();
	public Permiso findbyId (Integer id);
	public void create(Permiso Per );
	public void update(Permiso Per);
	public void delete(Permiso Per);
}
