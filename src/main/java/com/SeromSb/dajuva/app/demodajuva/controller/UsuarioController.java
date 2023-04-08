package com.SeromSb.dajuva.app.demodajuva.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SeromSb.dajuva.app.demodajuva.impl.RolImplement;
import com.SeromSb.dajuva.app.demodajuva.impl.UsuarioImplement;
import com.SeromSb.dajuva.app.demodajuva.modelo.Rol;
import com.SeromSb.dajuva.app.demodajuva.modelo.Usuario;

@Controller
@RequestMapping("/app/demodajuva/administrador/Usuario")
public class UsuarioController {

	@Autowired
	private UsuarioImplement usuImpl;
	@Autowired
	private RolImplement RolImpl;

	@GetMapping("/allUsuario")
	public String listUsario(Model model) {

		model.addAttribute("usuarios", usuImpl.findALL());

		return "/usuarios/listUsuario";
	}

	@GetMapping("/createUsuario")
	public String createFormUsuario(Model model) {
		Usuario usu = new Usuario();
		List<Rol> roles = RolImpl.findAll();

		model.addAttribute("usuario", usu);
		model.addAttribute("roles", roles);
		return "/usuarios/createUsuario";
	}

	@GetMapping("/updateUsuario/{id}")
	public String updateFormUsuario(@PathVariable int id, Model model) {

		Usuario usu = this.usuImpl.findbyId(id);
		List<Rol> roles = RolImpl.findAll();

		model.addAttribute("usuario", usu);
		model.addAttribute("roles", roles);
		return "/usuarios/editUsu";
	}

	@PostMapping("/saveUsuario")
	public String saveFormUsuario(@ModelAttribute Usuario usu) {

		Rol Roll = this.RolImpl.findbyId(usu.getFkIdRol().getId_rol());
		usu.setFkIdRol(Roll);
		this.usuImpl.update(usu);
		return "redirect:/app/demodajuva/administrador/Usuario/allUsuario";
	}

	@PostMapping("/usuario/{id}")
	public String updateUsuario(@PathVariable int id, @ModelAttribute Usuario usu) {
		Usuario UsuAlmacenado = usuImpl.findbyId(id);
		UsuAlmacenado.setId(id);
		UsuAlmacenado.setCiudad(usu.getCiudad());
		UsuAlmacenado.setClave(usu.getClave());
		UsuAlmacenado.setDireccion(usu.getDireccion());
		UsuAlmacenado.setEdad(usu.getEdad());
		UsuAlmacenado.setFecha_Nac(usu.getFecha_Nac());
		UsuAlmacenado.setTipo_Doc(usu.getTipo_Doc());
		UsuAlmacenado.setNum_Doc(usu.getNum_Doc());
		UsuAlmacenado.setTelefono(usu.getTelefono());
		UsuAlmacenado.setUsu_apellido(usu.getUsu_apellido());
		UsuAlmacenado.setUsu_nombre(usu.getUsu_nombre());
		UsuAlmacenado.setFkIdRol(usu.getFkIdRol());
		UsuAlmacenado.setEstado(null);
		usuImpl.update(UsuAlmacenado);
		return "redirect:/app/demodajuva/administrador/Usuario/allUsuario";
	}

	@PostMapping("/deleteUsuario/{id}")
	public String deleteFormUsuario(@PathVariable int id) {
		Usuario usu = usuImpl.findbyId(id);
		String estadoActual = usu.getEstado();
		String Cambiarestado = estadoActual.equals("Activado") ? "Desactivado" : "Activado";
		usu.setEstado(Cambiarestado);
		this.usuImpl.update(usu);
		return "redirect:/app/demodajuva/administrador/Usuario/allUsuario";
	}
}