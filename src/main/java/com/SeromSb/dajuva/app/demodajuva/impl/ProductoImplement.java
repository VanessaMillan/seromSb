package com.SeromSb.dajuva.app.demodajuva.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SeromSb.dajuva.app.demodajuva.modelo.Producto;
import com.SeromSb.dajuva.app.demodajuva.repositorio.IProductoRepository;
import com.SeromSb.dajuva.app.demodajuva.service.ProductoFacade;

@Service
public class ProductoImplement implements ProductoFacade{

	@Autowired
	IProductoRepository iProductoRepository;
	
	@Override
	public List<Producto> findALL(){
		return this.iProductoRepository.findAll();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public Producto findbyId(Integer id) {
		return this.iProductoRepository.getById(id);
	}
	
	@Override
	public void create(Producto Pro) {
		this.iProductoRepository.save(Pro);
	}
	
	@Override
	public void update(Producto Pro) {
		this.iProductoRepository.save(Pro);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void delete(Producto Pro) {
		Producto prot=this.iProductoRepository.getById(Pro.getId());
		this.iProductoRepository.delete(prot);
	}
}
