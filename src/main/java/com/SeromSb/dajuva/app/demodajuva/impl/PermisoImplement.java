package com.SeromSb.dajuva.app.demodajuva.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SeromSb.dajuva.app.demodajuva.modelo.Permiso;
import com.SeromSb.dajuva.app.demodajuva.repositorio.IPermisoRepository;
import com.SeromSb.dajuva.app.demodajuva.service.PermisoFacade;

@Service
public class PermisoImplement implements PermisoFacade{
	
	@Autowired
	IPermisoRepository iPermisoRepository;
	
	@Override
	public List<Permiso> findALL(){
		return this.iPermisoRepository.findAll();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public Permiso findbyId(Integer id) {
		return this.iPermisoRepository.getById(id);
	}
	
	@Override
	public void create(Permiso Per) {
		this.iPermisoRepository.save(Per);
	}
	
	@Override
	public void update(Permiso Per) {
		this.iPermisoRepository.save(Per);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void delete(Permiso Per) {
		Permiso permi=this.iPermisoRepository.getById(Per.getIdPermiso());
		this.iPermisoRepository.delete(permi);
	}

}
