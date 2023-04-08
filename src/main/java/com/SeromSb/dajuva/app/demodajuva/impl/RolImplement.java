package com.SeromSb.dajuva.app.demodajuva.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SeromSb.dajuva.app.demodajuva.modelo.Rol;
import com.SeromSb.dajuva.app.demodajuva.repositorio.IRolRepository;
import com.SeromSb.dajuva.app.demodajuva.service.RolFacade;
@Service
public class RolImplement implements RolFacade{
	
	
	@Autowired
	IRolRepository iRolRepository;
	
	@Override
	public List<Rol> findAll() {
		return this.iRolRepository.findAll();
	}

	@Override
	public Rol findbyId(Integer id) {
		return this.iRolRepository.findById(id).get();
	}

	@Override
	public void create(Rol Rol) {
		this.iRolRepository.save(Rol);
	}

	@Override
	public void update(Rol Rol) {
		this.iRolRepository.save(Rol);
	}
	
	@Override
	public void delete(int iD) {
		this.iRolRepository.deleteById(iD);
	}
}
