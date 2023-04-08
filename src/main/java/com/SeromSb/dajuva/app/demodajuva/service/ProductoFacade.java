package com.SeromSb.dajuva.app.demodajuva.service;

import java.util.List;

import com.SeromSb.dajuva.app.demodajuva.modelo.Producto;

public interface ProductoFacade {
	public List<Producto> findALL();
	public Producto findbyId (Integer id);
	public void create(Producto Pro);
	public void update(Producto Pro);
	public void delete(Producto Pro);
}

