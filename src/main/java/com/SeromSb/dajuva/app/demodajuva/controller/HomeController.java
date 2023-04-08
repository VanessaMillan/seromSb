package com.SeromSb.dajuva.app.demodajuva.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/conocenos")
	public String Conocenos() {

		return "conocenos";
	}

	@GetMapping("/index")
	public String index() {

		return "index";
	}

	@GetMapping({ "/", "" })
	public String verPaginaDeInicio() {
		return "index";
	}

	@GetMapping("/login")
	public String login() {

		return "formulario";
	}
	
	@GetMapping("/app/demodajuva/administrador/pagina-principal")
	public String administrador() {

		return "administrador";
	}
	

	@GetMapping("/app/demodajuva/vendedor")
	public String vendedor() {

		return "vendedor";
	}
}
