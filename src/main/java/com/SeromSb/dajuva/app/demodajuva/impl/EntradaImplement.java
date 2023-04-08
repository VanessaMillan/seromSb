package com.SeromSb.dajuva.app.demodajuva.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SeromSb.dajuva.app.demodajuva.modelo.Entrada;
import com.SeromSb.dajuva.app.demodajuva.repositorio.IEntradaRepository;
import com.SeromSb.dajuva.app.demodajuva.service.EntradaFacade;

@Service
public class EntradaImplement implements EntradaFacade{
	
	@Autowired
	IEntradaRepository iEntradaRepository;
	
	@Override
	public List<Entrada> findALL(){
		return this.iEntradaRepository.findAll();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public Entrada findbyId(Integer id) {
		return this.iEntradaRepository.getById(id);
	}
	
	@Override
	public void create(Entrada Ent) {
		this.iEntradaRepository.save(Ent);
	}
	
	@Override
	public void update(Entrada Ent) {
		this.iEntradaRepository.save(Ent);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void delete(Entrada Ent) {
		Entrada Entra=this.iEntradaRepository.getById(Ent.getId_Entradas());
		this.iEntradaRepository.delete(Entra);
	}
}
