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
import com.SeromSb.dajuva.app.demodajuva.impl.Detalle_pedidoImplement;
import com.SeromSb.dajuva.app.demodajuva.impl.PedidoImplement;
import com.SeromSb.dajuva.app.demodajuva.impl.ProductoImplement;
import com.SeromSb.dajuva.app.demodajuva.modelo.DetallePedido;
import com.SeromSb.dajuva.app.demodajuva.modelo.Pedido;
import com.SeromSb.dajuva.app.demodajuva.modelo.Producto;
import com.SeromSb.dajuva.app.demodajuva.modelo.ReportePedido;
import com.SeromSb.dajuva.app.demodajuva.modelo.Usuario;
import com.SeromSb.dajuva.app.demodajuva.service.ReportePedidoFacade;
import com.SeromSb.dajuva.app.demodajuva.service.UsuarioFacade;

import net.sf.jasperreports.engine.JRException;

@Controller
@RequestMapping(path = "/app/demodajuva/DetallePedido",method = {RequestMethod.PUT,RequestMethod.POST,RequestMethod.GET})
public class DetallePedidoController {
	
	@Autowired
	private Detalle_pedidoImplement DpeImpl;
	@Autowired
	private ProductoImplement ProImpl;
	@Autowired
	private PedidoImplement PedImp;
	@Autowired
	private ReportePedidoFacade reportePedidoF; 
	@Autowired
	private UsuarioFacade usuarioFacade; 
	
	@GetMapping("/allDetallePedido")

	public String listDetallePedido(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		
		Usuario user = usuarioFacade.findByCorreo(userDetails.getUsername()); 
		
		model.addAttribute("detallespedidos", this.DpeImpl.findALL());
		model.addAttribute("user", user);
		return "/detallePedido/listDetallePedido";
		
	}
	
	@GetMapping("/createDetallePedido")

	public String createFormDetallePedido(Model model) {
		DetallePedido dpe = new DetallePedido();
		List<Producto> producto = ProImpl.findALL();
		List<Pedido> ped = PedImp.findALL();
		
		model.addAttribute("detallepedido", dpe);
		model.addAttribute("producto", producto);
		model.addAttribute("pedido", ped);		
		return "/detallePedido/createDetallePedido";

	}
	
	@GetMapping("/updateDetallepedido/{id}")

	public String updateFormDetalle_pedido(@PathVariable int id, Model model) {

		DetallePedido dpe  = this.DpeImpl.findbyId(id);
		
		Pedido pedido = dpe.getFkPedido();
		
		int codPed = pedido.getId_pedido();
		
		Producto prod = dpe.getFkProducto();
		
		int codProd = prod.getId();
		
		List<Producto> producto = ProImpl.findALL();
		List<Pedido> ped = PedImp.findALL();

		model.addAttribute("detallepedido", dpe);
		model.addAttribute("producto", producto);
		model.addAttribute("pedido", ped);		
		model.addAttribute("codProd",codProd);
		model.addAttribute("codPed",codPed);
		return "/detallePedido/editDetalle";
	}
	
	@PostMapping("/saveDetallePedido")

	public String saveFormDetalle_pedido(@ModelAttribute DetallePedido dpe) {

		Producto Pro = this.ProImpl.findbyId(dpe.getFkProducto().getId());
		Pedido ped = this.PedImp.findbyId(dpe.getFkPedido().getId_pedido()); 
		
		dpe.setFkProducto(Pro);
		dpe.setFkPedido(ped); 
		dpe.setEstado("Activado");
		
		this.DpeImpl.update(dpe);
		return "redirect:/app/demodajuva/DetallePedido/allDetallePedido";

	}
	@PostMapping("/upgradeDetallePedido/{id}")
	public String updateDetallePedido(@PathVariable int id, @ModelAttribute("detallepedido") DetallePedido detalle_pedido) {
		DetallePedido detalle_ped = this.DpeImpl.findbyId(id);

		detalle_ped.setId_Dpedido(id);
		detalle_ped.setCant_productos(detalle_pedido.getCant_productos());
		detalle_ped.setEstado_pedido(detalle_pedido.getEstado_pedido());
		detalle_ped.setFkPedido(detalle_pedido.getFkPedido());
		detalle_ped.setFkProducto(detalle_pedido.getFkProducto());
		this.DpeImpl.update(detalle_ped);
		return "redirect:/app/demodajuva/DetallePedido/allDetallePedido";
	}

	
	@PostMapping("/deleteDetallepedido/{id}")

	public String deleteFormDetalle_pedido(@PathVariable int id) {
		DetallePedido dpe  = this.DpeImpl.findbyId(id);
		String estadoActual = dpe.getEstado();
		String Cambiarestado = estadoActual.equals("Activado")? "Desactivado": "Activado";
		dpe.setEstado(Cambiarestado);
		this.DpeImpl.update(dpe); 
		return "redirect:/app/demodajuva/DetallePedido/allDetallePedido";
	}
	
	@GetMapping("/detallePedido/download")
	public ResponseEntity<InputStreamResource> downloadReport(@RequestParam Map<String, Object> params,
			@RequestParam(name = "tipoReporte") String tipoReporte) throws IOException, JRException, SQLException {

		String fileName = "reporteDeVentas";

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


