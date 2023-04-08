package com.SeromSb.dajuva.app.demodajuva.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SeromSb.dajuva.app.demodajuva.modelo.Pedido;
import com.SeromSb.dajuva.app.demodajuva.repositorio.IPedidoRepository;
import com.SeromSb.dajuva.app.demodajuva.service.PedidoFacade;

@Service
public class PedidoImplement implements PedidoFacade {
	@Autowired
	IPedidoRepository iPedidoRepository;
	
	@Override
	public List<Pedido> findALL(){
		return this.iPedidoRepository.findAll();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public Pedido findbyId(Integer id) {
		return this.iPedidoRepository.getById(id);
	}
	
	@Override
	public void create(Pedido ped) {
		this.iPedidoRepository.save(ped);
	}
	
	@Override
	public void update(Pedido ped) {
		this.iPedidoRepository.save(ped);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void delete(Pedido ped) {
		Pedido Pedi=this.iPedidoRepository.getById(ped.getId_pedido());
		this.iPedidoRepository.delete(Pedi);
	}

}
