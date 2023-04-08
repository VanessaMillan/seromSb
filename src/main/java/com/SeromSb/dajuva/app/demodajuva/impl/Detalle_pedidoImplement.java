package com.SeromSb.dajuva.app.demodajuva.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SeromSb.dajuva.app.demodajuva.modelo.DetallePedido;
import com.SeromSb.dajuva.app.demodajuva.repositorio.IDetalle_pedidoRepository;
import com.SeromSb.dajuva.app.demodajuva.service.Detalle_pedidoFacade;
@Service
public class Detalle_pedidoImplement implements Detalle_pedidoFacade{
	@Autowired
	IDetalle_pedidoRepository iDetalle_pedidoRepository;
	
	@Override
	public List<DetallePedido> findALL(){
		return this.iDetalle_pedidoRepository.findAll();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public DetallePedido findbyId(Integer id) {
		return this.iDetalle_pedidoRepository.getById(id);
	}
	
	@Override
	public void create(DetallePedido Dpe) {
		this.iDetalle_pedidoRepository.save(Dpe);
	}
	
	@Override
	public void update(DetallePedido Dpe) {
		this.iDetalle_pedidoRepository.save(Dpe);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void delete(DetallePedido Dpe) {
		DetallePedido dpe=this.iDetalle_pedidoRepository.getById(Dpe.getId_Dpedido());
		this.iDetalle_pedidoRepository.delete(dpe);
	}
}
