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

import com.SeromSb.dajuva.app.demodajuva.impl.CategoriaImplement;
import com.SeromSb.dajuva.app.demodajuva.modelo.Categoria;


@Controller
@RequestMapping(path = "/app/demodajuva/categoria", method = { RequestMethod.PUT, RequestMethod.POST, RequestMethod.GET })
public class CategoriaController {
	@Autowired
	private CategoriaImplement CatImpl;

	@GetMapping("/allCategoria")

	public String listCategoria(Model model) {
		model.addAttribute("categoria", this.CatImpl.findAll());
		return "/categoria/listCategoria";
	}

	@GetMapping("/createCategoria")

	public String createFormCategoria(Model model) {
		Categoria cat = new Categoria();

		model.addAttribute("categoria", cat);
		return "/categoria/createCategoria";
		
	}

	@GetMapping("/updateCategoria/{id}")

	public String updateFormCategoria(@PathVariable int id, Model model) {

		Categoria cat = this.CatImpl.findbyId(id);

		model.addAttribute("categoria", cat);
		return "/categoria/editCategoria";
	}

	@PostMapping("/saveCategoria")

	public String saveFormCategoria(@ModelAttribute Categoria cat) {
		
		cat.setEstado("Activado");

		this.CatImpl.update(cat);
		return "redirect:/app/demodajuva/categoria/allCategoria";

	}
	
	@PostMapping("/categoria/{id}")
	public String updateRol(@PathVariable int id, @ModelAttribute Categoria cat) {
		Categoria CatAlmacenado = CatImpl.findbyId(id);
		CatAlmacenado.setIdCategoria(id); 
		CatAlmacenado.setCatProducto(cat.getCatProducto());
		CatImpl.update(CatAlmacenado); 
		return "redirect:/app/demodajuva/categoria/allCategoria";
	}

	@PostMapping("/deleteCategoria/{id}")

	public String deleteFormCategoria(@PathVariable int id) {
		Categoria cat = this.CatImpl.findbyId(id);
		String estadoActual = cat.getEstado();
		String Cambiarestado = estadoActual.equals("Activado") ? "Desactivado" : "Activado";
		cat.setEstado(Cambiarestado);
		this.CatImpl.update(cat);
		return "redirect:/app/demodajuva/categoria/allCategoria";
	}
}
/*import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.SeromSb.dajuva.app.demodajuva.impl.CategoriaImplement;
import com.SeromSb.dajuva.app.demodajuva.modelo.Categoria;


@Controller
@RequestMapping(path = "/app/demodajuva/Categoria",method = {RequestMethod.PUT,RequestMethod.POST,RequestMethod.GET})
public class CategoriaController {
	@Autowired
	private CategoriaImplement catImpl; 
	
	@PostMapping("/create")
	public ResponseEntity<Map<String,Object>> add(@RequestBody Map<String, Object> request){
		Categoria Catt = new Categoria ();
		Catt.setIdCategoria(0);
		Catt.setCatProducto(request.get("NombreCategoria").toString());
		catImpl.create(Catt);
		Map<String, Object> resp=new HashMap<>();
		resp.put("msn", "Registro Exitoso"); 
		resp.put("Status",HttpStatus.OK);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}
	
	@PostMapping("/update/{id}")
	public ResponseEntity<Map<String, Object>> update(@RequestBody Map<String, Object> request, @PathVariable int id) {
		Categoria Catt = new Categoria();
		Catt.setIdCategoria(0);
		Catt.setCatProducto(request.get("nombreCategoria").toString());
		catImpl.update(Catt);
		Map<String, Object> resp = new HashMap<>();
		resp.put("msn", "Actualizacion exitosa");
		resp.put("Status", HttpStatus.OK);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String, Object>> delete(@RequestBody Map<String, Object> request, 
			@PathVariable int id) {
		Categoria cat = new Categoria (); 
		cat.setIdCategoria(null);
		cat.setCatProducto(request.get("nombreCategoria").toString()); 
		catImpl.delete(cat); 
		Map<String, Object> resp = new HashMap<>(); 
		resp.put("msn", "Categoria eliminada"); 
		resp.put("Status", HttpStatus.OK); 
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}
}*/

