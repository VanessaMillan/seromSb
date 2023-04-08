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

import com.SeromSb.dajuva.app.demodajuva.impl.EntradaImplement;
import com.SeromSb.dajuva.app.demodajuva.modelo.Entrada;

@Controller
@RequestMapping(path = "/app/demodajuva/Entrada",method = {RequestMethod.PUT,RequestMethod.POST,RequestMethod.GET})
public class EntradaController {
	
	@Autowired
	private EntradaImplement EntImpl;
	
	@GetMapping("/allEntrada")
	
	public String listEntrada(Model model) {
		model.addAttribute("entradas", this.EntImpl.findALL());
		return "/entradas/listEntrada";
	}
	
	@GetMapping("/createEntrada")

	public String createFormEntrada(Model model) {
		Entrada Entra  = new Entrada();

		model.addAttribute("entrada", Entra);
		return "/entradas/createEntrada";

	}
	
	@GetMapping("/updateEntrada/{id}")

	public String updateFormEntrada(@PathVariable int id, Model model) {

		Entrada Entra = this.EntImpl.findbyId(id);

		model.addAttribute("entrada", Entra);
		return "entradas/editEntrada";
	}
	
	@PostMapping("/saveEntrada")

	public String saveFormEntrada(@ModelAttribute Entrada Entra) {
		
		Entra.setEstado("Activado");
		
		this.EntImpl.create(Entra);
		return "redirect:/app/demodajuva/Entrada/allEntrada";

	}
	
	@PostMapping("/entrada/{id}")
	public String updateEntrada(@PathVariable int id, @ModelAttribute Entrada entrada) {
		Entrada entradaOld = EntImpl.findbyId(id);
		entradaOld.setId_Entradas(id);
		entradaOld.setEnt_CantidadProd(entrada.getEnt_CantidadProd());
		entradaOld.setEnt_Fecha(entrada.getEnt_Fecha());
		entradaOld.setEnt_NomProd(entrada.getEnt_NomProd());
		this.EntImpl.update(entradaOld);
		return "redirect:/app/demodajuva/Entrada/allEntrada";
	}
	
	@PostMapping("/deleteEntrada/{id}")

	public String deleteFormEntrada(@PathVariable int id) {
		Entrada Entra = this.EntImpl.findbyId(id);
		String estadoActual = Entra.getEstado();
		String Cambiarestado = estadoActual.equals("Activado")? "Desactivado": "Activado";
		Entra.setEstado(Cambiarestado);
		this.EntImpl.update(Entra);
		return "redirect:/app/demodajuva/Entrada/allEntrada";
	}
}

/*@Controller
@RequestMapping(path = "/app/demodajuva/Entrada",method = {RequestMethod.PUT,RequestMethod.POST,RequestMethod.GET})
public class EntradaController {
	@Autowired
	private EntradaImplement entImpl;
	@PostMapping("/create")
	public ResponseEntity<Map<String,Object>> add(@RequestBody Map<String, Object> request){
		Entrada Entra=new Entrada();
		Entra.setId_Entradas(0);
		Entra.setEnt_CantidadProd(Integer.parseInt(request.get("CantidadProducto").toString()));
		Entra.setEnt_Fecha(Utility.convertiFecha(request.get("EntradaFecha").toString()));
		Entra.setEnt_NomProd(request.get("NomProEntrada").toString());
		entImpl.create(Entra);
		Map<String, Object> resp=new HashMap<>();
		resp.put("msn", "Registro Exitoso");
		resp.put("Status", HttpStatus.OK);
		return new ResponseEntity<>(resp, HttpStatus.OK);	
	}
	@PostMapping("/update/{id}")
	public ResponseEntity<Map<String,Object>> update(@RequestBody Map<String, Object> request,
			@PathVariable int id){
		Entrada Entra = new Entrada();
		Entra.setId_Entradas(0);
		Entra.setEnt_CantidadProd(Integer.parseInt(request.get("CantidadProducto").toString()));
		Entra.setEnt_Fecha(Utility.convertiFecha(request.get("EntradaFecha").toString()));
		Entra.setEnt_NomProd(request.get("NomProEntrada").toString());
		entImpl.update(Entra);
		Map<String, Object> resp=new HashMap<>();
		resp.put("msn", "Registro Exitoso");
		resp.put("Status", HttpStatus.OK);
		return new ResponseEntity<>(resp, HttpStatus.OK);	
		}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String,Object>> delete(@RequestBody Map<String, Object> request,
			@PathVariable int id){
		Entrada Entra = new Entrada();
		Entra.setId_Entradas(0);
		Entra.setEnt_CantidadProd(Integer.parseInt(request.get("CantidadProducto").toString()));
		Entra.setEnt_Fecha(Utility.convertiFecha(request.get("EntradaFecha").toString()));
		Entra.setEnt_NomProd(request.get("NomProEntrada").toString());
		entImpl.delete(Entra);
		Map<String, Object> resp=new HashMap<>();
		resp.put("msn", "Registro Exitoso");
		resp.put("Status", HttpStatus.OK);
		return new ResponseEntity<>(resp, HttpStatus.OK);	
	}
}*/
