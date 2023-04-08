package com.SeromSb.dajuva.app.demodajuva.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SeromSb.dajuva.app.demodajuva.modelo.Rol;
import com.SeromSb.dajuva.app.demodajuva.service.RolFacade;

@Controller
@RequestMapping("/app/demodajuva/administrador/Rol")
public class RolController {

	@Autowired
	private RolFacade rolFacade;

	@GetMapping("/allRol")
	public String listRol(Model model) {
		model.addAttribute("roles", this.rolFacade.findAll());
		return "/rol/listRol";
	}

	@GetMapping("/createRol")

	public String createFormUsuario(Model model) {
		Rol rol = new Rol();

		model.addAttribute("rol", rol);
		return "/rol/createRol";

	}

	@GetMapping("/updateRol/{id}")
	public String updateFormRol(@PathVariable int id, Model model) {

		try {
			Rol rol = this.rolFacade.findbyId(id);

			model.addAttribute("rol", rol);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "/rol/editRol";
	}

	@PostMapping("/saveRol")
	public String saveFormRol(@ModelAttribute Rol Roll) {
		
		Roll.setEstado("Activado");

		this.rolFacade.create(Roll);
		return "redirect:/app/demodajuva/administrador/Rol/allRol";

	}

	@PostMapping("/roles/{id}")
	public String updateRol(@PathVariable int id, @ModelAttribute Rol Roll) {
		Rol rolAlmacenado = rolFacade.findbyId(id);
		rolAlmacenado.setId_rol(id);
		rolAlmacenado.setRol_nombre(Roll.getRol_nombre());
		rolFacade.update(rolAlmacenado);
		return "redirect:/app/demodajuva/administrador/Rol/allRol";
	}

	@GetMapping("/deleteRol/{id}")
	public String deleteFormRol(@PathVariable int id) {
		Rol Roll = this.rolFacade.findbyId(id);
		String estadoActual = Roll.getEstado();
		String Cambiarestado = estadoActual.equals("Activado") ? "Desactivado" : "Activado";
		Roll.setEstado(Cambiarestado);
		this.rolFacade.update(Roll);
		return "redirect:/app/demodajuva/administrador/Rol/allRol";
	}
}