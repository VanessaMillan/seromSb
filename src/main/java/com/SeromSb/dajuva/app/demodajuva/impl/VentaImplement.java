package com.SeromSb.dajuva.app.demodajuva.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SeromSb.dajuva.app.demodajuva.modelo.Venta;
import com.SeromSb.dajuva.app.demodajuva.repositorio.IVentaRepository;
import com.SeromSb.dajuva.app.demodajuva.service.VentaFacade;

@Service
public class VentaImplement implements VentaFacade{

	@Autowired
	IVentaRepository iVentaRepository;
	
	@Override
	public List<Venta> findALL(){
		return this.iVentaRepository.findAll();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public Venta findbyId(Integer id) {
		return this.iVentaRepository.getById(id);
	}
	
	@Override
	public void create(Venta Ven) {
		this.iVentaRepository.save(Ven);
	}
	
	@Override
	public void update(Venta Ven) {
		this.iVentaRepository.save(Ven);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void delete(Venta Ven) {
		Venta vent=this.iVentaRepository.getById(Ven.getId());
		this.iVentaRepository.delete(vent);
	}

	public static Venta findById(int parseInt) {
		// TODO Auto-generated method stub
		return null;
	}
}
