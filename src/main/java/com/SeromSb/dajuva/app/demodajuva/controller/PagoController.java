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

import com.SeromSb.dajuva.app.demodajuva.impl.PagoImplement;
import com.SeromSb.dajuva.app.demodajuva.impl.VentaImplement;
import com.SeromSb.dajuva.app.demodajuva.modelo.Pago;
import com.SeromSb.dajuva.app.demodajuva.modelo.Usuario;
import com.SeromSb.dajuva.app.demodajuva.modelo.Venta;
import com.SeromSb.dajuva.app.demodajuva.service.UsuarioFacade;

@Controller
@RequestMapping(path = "/app/demodajuva/Pago",method = {RequestMethod.PUT,RequestMethod.POST,RequestMethod.GET})
public class PagoController {
	
	@Autowired
     private PagoImplement PagImpl;
	@Autowired
   	 private VentaImplement VenImpl; 
	@Autowired
	private UsuarioFacade usuarioFacade; 

	@GetMapping("/allPago")

	public String listPago(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		
		Usuario user = usuarioFacade.findByCorreo(userDetails.getUsername()); 

		model.addAttribute("pagos", this.PagImpl.findALL());
		model.addAttribute("user", user);
		return "/pagos/listPago";
	}

	@GetMapping("/createPago")

	public String createFormPago(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		Pago pag = new Pago();
		List<Venta> venta = VenImpl.findALL(); 

		model.addAttribute("pago", pag);
		model.addAttribute("venta", venta);
		return "/pagos/createPago";
	}

	@GetMapping("/updatePago/{id}")

	public String updateFormPago(@PathVariable int id, Model model) {

		Pago pag = this.PagImpl.findbyId(id);
		List<Venta> venta = VenImpl.findALL();

		model.addAttribute("pago", pag);
		model.addAttribute("venta", venta);
		return "/pagos/editPago";
	}

	@PostMapping("/savePago")

	public String saveFormPago(@ModelAttribute Pago pag) {
		
		Venta vent = this.VenImpl.findbyId(pag.getPagVenta().getId());
		pag.setPagVenta(vent);
		this.PagImpl.update(pag);
		return "redirect:/app/demodajuva/Pago/allPago";
	}
	
	@PostMapping("/pago/{id}")
	public String updatePago(@PathVariable int id, @ModelAttribute Pago pag) {
		Pago pagoOld = PagImpl.findbyId(id);
		pagoOld.setId(id);
		pagoOld.setIva(pag.getIva());
		pagoOld.setMetPago(pag.getMetPago());
		pagoOld.setPagVenta(pag.getPagVenta());
		this.PagImpl.update(pagoOld);
		return "redirect:/app/demodajuva/Pago/allPago";
	}
	
	@PostMapping("/deletePago/{id}")
	
	public String deleteFormPago(@PathVariable int id) {
		Pago pag = this.PagImpl.findbyId(id);
		String estadoActual = pag.getEstado();
		String Cambiarestado = estadoActual.equals("Activado")? "Desactivado": "Activado";
		pag.setEstado(Cambiarestado);
		this.PagImpl.update(pag);
		return "redirect:/app/demodajuva/Pago/allPago";
	}
	
}
