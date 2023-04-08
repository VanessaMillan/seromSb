package com.SeromSb.dajuva.app.demodajuva.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SeromSb.dajuva.app.demodajuva.modelo.Categoria;
import com.SeromSb.dajuva.app.demodajuva.repositorio.ICategoriaRepository;
import com.SeromSb.dajuva.app.demodajuva.service.CategoriaFacade;

@Service
public class CategoriaImplement implements CategoriaFacade {

	
	@Autowired
	ICategoriaRepository iCategoriaRepository;
	
	@Override
	public List<Categoria> findAll(){
		return this.iCategoriaRepository.findAll();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public Categoria findbyId(Integer id){
		return this.iCategoriaRepository.getById(id);
	}
	
	@Override
	public void create(Categoria Cat) {
		this.iCategoriaRepository.save(Cat);
	}
	
	@Override
	public void update(Categoria Cat) {
		this.iCategoriaRepository.save(Cat);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void delete(Categoria Cat) {
		Categoria Catt=this.iCategoriaRepository.getById(Cat.getIdCategoria());
		this.iCategoriaRepository.delete(Catt);
	}

	public static Categoria findById(Categoria fkCategoria) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Categoria findById(int parseInt) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
