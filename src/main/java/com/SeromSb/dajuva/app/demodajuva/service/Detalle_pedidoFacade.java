package com.SeromSb.dajuva.app.demodajuva.service;

import java.util.List;

import com.SeromSb.dajuva.app.demodajuva.modelo.DetallePedido;


	public interface Detalle_pedidoFacade {
		public List<DetallePedido> findALL();
		public DetallePedido findbyId (Integer id);
		public void create(DetallePedido Dped );
		public void update(DetallePedido Dped);
		public void delete(DetallePedido Dped);
	}

