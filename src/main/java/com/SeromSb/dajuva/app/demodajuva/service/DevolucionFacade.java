package com.SeromSb.dajuva.app.demodajuva.service;

import java.util.List;

import com.SeromSb.dajuva.app.demodajuva.modelo.Devolucion;


public interface DevolucionFacade {
	
	public List<Devolucion> findALL();
	public Devolucion findbyId (Integer id);
	public void create(Devolucion Dev );
	public void update(Devolucion Dev);
	public void delete(Devolucion Dev);
}
