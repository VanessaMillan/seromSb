package com.SeromSb.dajuva.app.demodajuva.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SeromSb.dajuva.app.demodajuva.modelo.Devolucion;
import com.SeromSb.dajuva.app.demodajuva.repositorio.IDevolucionRepository;
import com.SeromSb.dajuva.app.demodajuva.service.DevolucionFacade;

@Service
public class DevolucionImplement implements DevolucionFacade {

	@Autowired
	IDevolucionRepository iDevolucionRepository;
	
	@Override
	public List<Devolucion> findALL(){
		return this.iDevolucionRepository.findAll();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public Devolucion findbyId(Integer id) {
		return this.iDevolucionRepository.getById(id);
	}
	
	@Override
	public void create(Devolucion dev) {
		this.iDevolucionRepository.save(dev);
	}
	
	@Override
	public void update(Devolucion dev) {
		this.iDevolucionRepository.save(dev);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void delete(Devolucion dev) {
		Devolucion devo =this.iDevolucionRepository.getById(dev.getId_devolucion());
		this.iDevolucionRepository.delete(devo);
	}

	public static Devolucion findById(int parseInt) {
		// TODO Auto-generated method stub
		return null;
	}


}
