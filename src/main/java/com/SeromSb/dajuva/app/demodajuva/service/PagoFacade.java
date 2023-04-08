package com.SeromSb.dajuva.app.demodajuva.service;

import java.util.List;

import com.SeromSb.dajuva.app.demodajuva.modelo.Pago;

public interface PagoFacade {
	public List<Pago> findALL();
	public Pago findbyId (Integer id);
	public void create(Pago Pago );
	public void update(Pago Pago);
	public void delete(Pago Pago);
}


