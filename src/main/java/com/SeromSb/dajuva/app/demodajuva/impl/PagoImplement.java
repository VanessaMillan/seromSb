package com.SeromSb.dajuva.app.demodajuva.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SeromSb.dajuva.app.demodajuva.modelo.Pago;
import com.SeromSb.dajuva.app.demodajuva.repositorio.IPagoRepository;
import com.SeromSb.dajuva.app.demodajuva.service.PagoFacade;

@Service
public class PagoImplement implements PagoFacade {

	@Autowired
	IPagoRepository iPagoRepository; 
	
	@Override
	public List<Pago> findALL(){
		return this.iPagoRepository.findAll();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public Pago findbyId(Integer id) {
		return this.iPagoRepository.getById(id);
	}
	
	@Override
	public void create(Pago pag) {
		this.iPagoRepository.save(pag);
	}
	
	@Override
	public void update(Pago pag) {
		this.iPagoRepository.save(pag);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void delete(Pago pag) {
		Pago pago=this.iPagoRepository.getById(pag.getId());
		this.iPagoRepository.delete(pago);
	}
}
