package com.SeromSb.dajuva.app.demodajuva.service;

import java.util.List;

import com.SeromSb.dajuva.app.demodajuva.modelo.Pedido;


public interface PedidoFacade {
	public List<Pedido> findALL();
	public Pedido findbyId (Integer id);
	public void create(Pedido Ped );
	public void update(Pedido Ped);
	public void delete(Pedido Ped);
}
