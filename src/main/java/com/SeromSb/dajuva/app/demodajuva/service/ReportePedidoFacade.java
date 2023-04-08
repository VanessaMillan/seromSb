package com.SeromSb.dajuva.app.demodajuva.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import com.SeromSb.dajuva.app.demodajuva.modelo.ReportePedido;

import net.sf.jasperreports.engine.JRException;

public interface ReportePedidoFacade {

	ReportePedido obtenerReportePedido(Map<String, Object> params, String tipo, String nombre)
			throws JRException, IOException, SQLException;

}
