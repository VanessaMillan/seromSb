package com.SeromSb.dajuva.app.demodajuva.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.SeromSb.dajuva.app.demodajuva.impl.DevolucionImplement;
import com.SeromSb.dajuva.app.demodajuva.impl.VentaImplement;
import com.SeromSb.dajuva.app.demodajuva.modelo.Devolucion;
import com.SeromSb.dajuva.app.demodajuva.modelo.Usuario;
import com.SeromSb.dajuva.app.demodajuva.modelo.Venta;
import com.SeromSb.dajuva.app.demodajuva.service.UsuarioFacade;

@Controller
@RequestMapping(path = "/app/demodajuva/Devolucion", method = { RequestMethod.PUT, RequestMethod.POST,
		RequestMethod.GET })
public class DevolucionController {

	@Autowired
	private DevolucionImplement DevImpl;
	@Autowired
	private VentaImplement VentImpl;
	@Autowired
	private UsuarioFacade usuarioFacade;

	@GetMapping("/allDevolucion")
	public String listVenta(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		
		Usuario user = usuarioFacade.findByCorreo(userDetails.getUsername()); 

		model.addAttribute("devoluciones", DevImpl.findALL());
		model.addAttribute("user", user);
		return "/devoluciones/listDevolucion";
	}

	@GetMapping("/createDevolucion")
	public String createFormDevolucion(Model model) {
		Devolucion dev = new Devolucion();
		List<Venta> venta = VentImpl.findALL();

		model.addAttribute("devolucion", dev);
		model.addAttribute("venta", venta);
		return "/devoluciones/createDevolucion";
	}

	@GetMapping("/updateDevolucion/{id}")

	public String updateFormUsuario(@PathVariable int id, Model model) {

		Devolucion dev = this.DevImpl.findbyId(id);
		
		Venta ventaOld = dev.getFkventa();
		
		String codVen = ventaOld.getCodVenta();
		
		model.addAttribute("codVen", codVen);
		model.addAttribute("devolucion", dev);
		
		List<Venta> venta = VentImpl.findALL();
		model.addAttribute("ventas", venta);

		return "/devoluciones/editDevolucion";
	}

	@PostMapping("/saveDevolucion")

	public String saveFormDevolucion(@ModelAttribute Devolucion dev) {

		Venta vent = this.VentImpl.findbyId(dev.getFkventa().getId());
		dev.setFkventa(vent);
		dev.setEstado("Activado");
		
		this.DevImpl.update(dev);
		return "redirect:/app/demodajuva/Devolucion/allDevolucion";
	}

	@PostMapping("/devolucion/{id}")
	public String updatePedido(@PathVariable int id, @ModelAttribute Devolucion devolucion) {
		Devolucion devolucionOld = this.DevImpl.findbyId(id);
		
		devolucionOld.setId_devolucion(id);
		devolucionOld.setDescripcion(devolucion.getDescripcion());
		devolucionOld.setFkventa(devolucion.getFkventa());
		
		this.DevImpl.update(devolucionOld);

		return "redirect:/app/demodajuva/Devolucion/allDevolucion";
	}

	@PostMapping("/deleteDevolucion/{id}")

	public String deleteFormDevolucion(@PathVariable int id) {
		Devolucion dev = this.DevImpl.findbyId(id);
		String estadoActual = dev.getEstado();
		String Cambiarestado = estadoActual.equals("Activado") ? "Desactivado" : "Activado";
		dev.setEstado(Cambiarestado);
		this.DevImpl.update(dev);
		return "redirect:/app/demodajuva/Devolucion/allDevolucion";
	}
}
