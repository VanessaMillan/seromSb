package com.SeromSb.dajuva.app.demodajuva.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SeromSb.dajuva.app.demodajuva.modelo.Usuario;
import com.SeromSb.dajuva.app.demodajuva.service.UsuarioFacade;

@Controller
@RequestMapping("/registro")
public class RegistroController {

	@Autowired
	private UsuarioFacade usuarioFacade;

	@ModelAttribute("usuario")
	public Usuario retornarNuevoUsuario() {
		return new Usuario();
	}

	@GetMapping
	public String mostrarFormularioDeRegistro() {
		return "registro";
	}

	@PostMapping
	public String registrarCuentaDeUsuario(@ModelAttribute("usuario") Usuario usuario) {
		usuarioFacade.update(usuario);
		return "redirect:/registro?exito";
	}
}
