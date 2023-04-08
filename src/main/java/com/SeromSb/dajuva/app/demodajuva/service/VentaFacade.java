package com.SeromSb.dajuva.app.demodajuva.service;

import java.util.List;

import com.SeromSb.dajuva.app.demodajuva.modelo.Venta;


public interface VentaFacade {
	public List<Venta> findALL();
	public Venta findbyId (Integer id);
	public void create(Venta Ven );
	public void update(Venta Ven);
	public void delete(Venta Ven);
}
