package com.SeromSb.dajuva.app.demodajuva.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SeromSb.dajuva.app.demodajuva.commons.JasperReportManager;
import com.SeromSb.dajuva.app.demodajuva.modelo.ReportePedido;
import com.SeromSb.dajuva.app.demodajuva.service.ReportePedidoFacade;

import net.sf.jasperreports.engine.JRException;

@Service
public class ReportePedidoImplement implements ReportePedidoFacade {
	
	@Autowired
	private JasperReportManager reportManager;

	@Autowired
	private DataSource dataSource;

	@Override
	public ReportePedido obtenerReportePedido(Map<String, Object> params, String tipo, String nombre)
			throws JRException, IOException, SQLException {
		ReportePedido dto = new ReportePedido();
		String extension = tipo.equalsIgnoreCase(com.SeromSb.dajuva.app.demodajuva.enums.TipoReporteEnum.EXCEL.toString())
				? ".xlsx"
				: ".pdf";
		dto.setFileName(nombre + extension);

		ByteArrayOutputStream stream = reportManager.export(nombre, tipo, params, dataSource.getConnection());

		byte[] bs = stream.toByteArray();
		dto.setStream(new ByteArrayInputStream(bs));
		dto.setLength(bs.length);

		return dto;
	}

}
