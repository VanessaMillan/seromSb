package com.SeromSb.dajuva.app.demodajuva.service;

import java.util.List;

import com.SeromSb.dajuva.app.demodajuva.modelo.Categoria;


public interface CategoriaFacade {
	public List<Categoria> findAll();
	public Categoria findbyId (Integer id);
	public void create(Categoria Cat);
	public void update(Categoria Cat);
	public void delete(Categoria Cat);
}
