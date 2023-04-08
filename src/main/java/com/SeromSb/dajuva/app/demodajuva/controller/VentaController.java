package com.SeromSb.dajuva.app.demodajuva.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.SeromSb.dajuva.app.demodajuva.enums.TipoReporteEnum;
import com.SeromSb.dajuva.app.demodajuva.impl.PedidoImplement;
import com.SeromSb.dajuva.app.demodajuva.modelo.Pedido;
import com.SeromSb.dajuva.app.demodajuva.modelo.ReportePedido;
import com.SeromSb.dajuva.app.demodajuva.modelo.Usuario;
import com.SeromSb.dajuva.app.demodajuva.modelo.Venta;
import com.SeromSb.dajuva.app.demodajuva.service.ReportePedidoFacade;
import com.SeromSb.dajuva.app.demodajuva.service.UsuarioFacade;
import com.SeromSb.dajuva.app.demodajuva.service.VentaFacade;

import net.sf.jasperreports.engine.JRException;

@Controller
@RequestMapping(path = "/app/demodajuva/Venta", method = { RequestMethod.PUT, RequestMethod.POST, RequestMethod.GET })
public class VentaController {

	@Autowired
	private VentaFacade ventaFacade;
	@Autowired
	private PedidoImplement PedImpl;
	@Autowired
	private ReportePedidoFacade reportePedidoF; 
	@Autowired
	private UsuarioFacade usuarioFacade; 

	@GetMapping("/allVenta")
	public String listVenta(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		
		Usuario user = usuarioFacade.findByCorreo(userDetails.getUsername()); 

		model.addAttribute("ventas", this.ventaFacade.findALL());
		model.addAttribute("user", user);
		return "/venta/listVenta";
	}

	@GetMapping("/createVenta")

	public String createFormVenta(Model model) {
		Venta vent = new Venta();
		List<Pedido> pedido = PedImpl.findALL();

		model.addAttribute("venta", vent);
		model.addAttribute("pedido", pedido);
		return "/venta/createVenta";

	}

	@GetMapping("/updateVenta/{id}")
	public String updateFormVenta(@PathVariable int id, Model model) {

	try {
		Venta vent = this.ventaFacade.findbyId(id);

		model.addAttribute("venta", vent);
	} catch (Exception e) {
		System.out.println(e.getMessage());
	}
		return "/venta/editVenta";
	}

	@PostMapping("/saveVenta")

	public String saveFormVenta(@ModelAttribute Venta vent) {
		
		this.ventaFacade.create(vent); 
		return "redirect:/app/demodajuva/Venta/allVenta";

	}
	
	@PostMapping("/venta/{id}")
	public String updateVenta(@PathVariable int id, @ModelAttribute Venta venta) {
		Venta ventaOld = this.ventaFacade.findbyId(id);

		ventaOld.setId(id);
		ventaOld.setFechaVent(venta.getFechaVent());
		ventaOld.setSubTotal(venta.getSubTotal());
		ventaFacade.update(ventaOld);
		return "redirect:/app/demodajuva/Venta/allVenta";
	}

	@GetMapping("/deleteVenta/{id}")

	public String deleteFormVenta(@PathVariable int id) {
		Venta vent = this.ventaFacade.findbyId(id);
		String estadoActual = vent.getEstado();
		String Cambiarestado = estadoActual.equals("Activado")? "Desactivado": "Activado";
		vent.setEstado(Cambiarestado);
		this.ventaFacade.update(vent);
		return "redirect:/app/demodajuva/Venta/allVenta";
	}
	
	@GetMapping("/venta/download")
	public ResponseEntity<InputStreamResource> downloadReport(@RequestParam Map<String, Object> params,
			@RequestParam(name = "tipoReporte") String tipoReporte) throws IOException, JRException, SQLException {

		String fileName = "reporteVent";

		ReportePedido reportePed = reportePedidoF.obtenerReportePedido(params, tipoReporte, fileName); 
		InputStreamResource streamResource = new InputStreamResource(reportePed.getStream());
		MediaType mediaType = null;

		if (tipoReporte.equalsIgnoreCase(TipoReporteEnum.EXCEL.name())) {
        mediaType = MediaType.APPLICATION_OCTET_STREAM;

		} else {
			mediaType = MediaType.APPLICATION_PDF;
		}

		return ResponseEntity.ok().header("Content-Disposition", "inline; filename=\"" + reportePed.getFileName() + "\"").contentLength(reportePed.getLength()).contentType(mediaType).body(streamResource);
	}
}

