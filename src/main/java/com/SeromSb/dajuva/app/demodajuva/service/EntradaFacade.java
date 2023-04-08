package com.SeromSb.dajuva.app.demodajuva.service;

import java.util.List;

import com.SeromSb.dajuva.app.demodajuva.modelo.Entrada;



public interface EntradaFacade {
	public List<Entrada> findALL();
	public Entrada findbyId (Integer id);
	public void create(Entrada Ent );
	public void update(Entrada Ent);
	public void delete(Entrada Ent);
}
