package com.SeromSb.dajuva.app.demodajuva.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.SeromSb.dajuva.app.demodajuva.impl.PermisoImplement;
import com.SeromSb.dajuva.app.demodajuva.modelo.Permiso;

@Controller
@RequestMapping(path = "/app/demodajuva/administrador/Permiso", method = { RequestMethod.PUT, RequestMethod.POST, RequestMethod.GET })
public class PermisoController {

	@Autowired
	private PermisoImplement PerImpl;

	@GetMapping("/allPermiso")

	public String listPermiso(Model model) {
		model.addAttribute("permisos", this.PerImpl.findALL());
		return "/permiso/listPermiso";
	}

	@GetMapping("/createPermiso")

	public String createFormUsuario(Model model) {
		Permiso permi = new Permiso();

		model.addAttribute("permiso", permi);
		return "/permiso/createPermiso";

	}

	@GetMapping("/updatePermiso/{id}")

	public String updateFormPermiso(@PathVariable int id, Model model) {

		Permiso permi = this.PerImpl.findbyId(id);

		model.addAttribute("permiso", permi);
		return "/permiso/editPermiso";
	}

	@PostMapping("/savePermiso")
	public String saveFormRol(@ModelAttribute Permiso permi) {

		this.PerImpl.update(permi);
		return "redirect:/app/demodajuva/administrador/Permiso/allPermiso";

	}

	@PostMapping("/permiso/{id}")
	public String updatePermiso(@PathVariable int id, @ModelAttribute Permiso permi) {
		Permiso perAlmacenado = PerImpl.findbyId(id);
		perAlmacenado.setIdPermiso(id);
		perAlmacenado.setPer_Desc(permi.getPer_Desc());
		perAlmacenado.setEstado(null);
		PerImpl.update(perAlmacenado);
		return "redirect:/app/demodajuva/administrador/Permiso/allPermiso";
	}

	@PostMapping("/deletePermiso/{id}")

	public String deleteFormPermiso(@PathVariable int id) {
		Permiso permi = this.PerImpl.findbyId(id);
		String estadoActual = permi.getEstado();
		String Cambiarestado = estadoActual.equals("Activado")? "Desactivado": "Activado";
		permi.setEstado(Cambiarestado);
		this.PerImpl.update(permi);
		return "redirect:/app/demodajuva/administrador/Permiso/allPermiso";
	}
}

/* @Controller */
/*
 * @RequestMapping(path = "/app/demodajuva/Permiso",method =
 * {RequestMethod.PUT,RequestMethod.POST,RequestMethod.GET}) public class
 * PermisoController { /*@Autowired private PermisoImplement perImpl;
 * 
 * @PostMapping("/create") public ResponseEntity<Map<String,Object>>
 * add(@RequestBody Map<String, Object> request){ Permiso permi=new Permiso();
 * permi.setIdPermiso(0);
 * permi.setPer_Desc(request.get("PermisoDesc").toString());
 * perImpl.create(permi); } }
 */