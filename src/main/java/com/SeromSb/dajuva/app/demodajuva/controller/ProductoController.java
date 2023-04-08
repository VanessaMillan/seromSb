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

import com.SeromSb.dajuva.app.demodajuva.impl.CategoriaImplement;
import com.SeromSb.dajuva.app.demodajuva.impl.ProductoImplement;
import com.SeromSb.dajuva.app.demodajuva.modelo.Categoria;
import com.SeromSb.dajuva.app.demodajuva.modelo.Producto;
import com.SeromSb.dajuva.app.demodajuva.modelo.Usuario;
import com.SeromSb.dajuva.app.demodajuva.service.UsuarioFacade;

@Controller
@RequestMapping(path = "/app/demodajuva/Producto", method = { RequestMethod.PUT, RequestMethod.POST,RequestMethod.GET })
public class ProductoController {

	@Autowired
	private ProductoImplement ProImpl;
	@Autowired
	private CategoriaImplement CatImpl;
	@Autowired
	private UsuarioFacade usuarioFacade;

	@GetMapping("/allProducto")

	public String listProducto(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		
		Usuario user = usuarioFacade.findByCorreo(userDetails.getUsername()); 

		model.addAttribute("producto", this.ProImpl.findALL());
		model.addAttribute("user", user);
		return "/producto/listProducto";
	}

	@GetMapping("/createProducto")

	public String createFormProducto(Model model) {
		Producto Pro = new Producto();
		List<Categoria> categoria = CatImpl.findAll();

		model.addAttribute("producto", Pro);
		model.addAttribute("categoria", categoria);
		return "/producto/createProducto";
	}

	@GetMapping("/updateProducto/{id}")

	public String updateFormProducto(@PathVariable int id, Model model) {

		Producto Pro = this.ProImpl.findbyId(id);
		List<Categoria> categoria = CatImpl.findAll();

		model.addAttribute("producto", Pro);
		model.addAttribute("categoria", categoria);

		return "/producto/editProducto";
	}

	@PostMapping("/saveProducto")

	public String saveFormProducto(@ModelAttribute Producto Pro) {

		Categoria Catt = this.CatImpl.findbyId(Pro.getFkCategoria().getIdCategoria());
		Pro.setFkCategoria(Catt);
		this.ProImpl.update(Pro);
		return "redirect:/app/demodajuva/Producto/allProducto";
	}

	@PostMapping("/producto/{id}")
	public String updateProducto(@PathVariable int id, @ModelAttribute Producto producto) {
		Producto productoOld = this.ProImpl.findbyId(id);

		productoOld.setId(id);
		productoOld.setProdNombre(producto.getProdNombre());
		productoOld.setProdMarca(producto.getProdMarca());
		productoOld.setProdTalla(producto.getProdTalla());
		productoOld.setProdColor(producto.getProdColor());
		productoOld.setProdPrecio(producto.getProdPrecio());
		productoOld.setFkCategoria(producto.getFkCategoria());
		this.ProImpl.update(productoOld);
		return "redirect:/app/demodajuva/Producto/allProducto";
	}

	@PostMapping("/deleteProducto/{id}")

	public String deleteFormProducto(@PathVariable int id) {
		Producto Pro = this.ProImpl.findbyId(id);
		String estadoActual = Pro.getEstado();
		String Cambiarestado = estadoActual.equals("Activado")? "Desactivado": "Activado";
		Pro.setEstado(Cambiarestado);
		this.ProImpl.update(Pro);
		return "redirect:/app/demodajuva/Producto/allProducto";
	}
}

/*
 * @RequestMapping(path = "/app/demodajuva/Producto",method =
 * {RequestMethod.PUT,RequestMethod.POST,RequestMethod.GET}) public class
 * ProductoController {
 * 
 * @Autowired private ProductoImplement proImpl;
 * 
 * @PostMapping("/create") public ResponseEntity<Map<String,Object>>
 * add(@RequestBody Map<String, Object> request){ Producto prot=new Producto();
 * prot.setId(0); prot.setProdColor(request.get("ColorProducto").toString());
 * prot.setProdMarca(request.get("MarcaProducto").toString());
 * prot.setProdNombre(request.get("NombreProducto").toString());
 * prot.setProdPrecio(Double.parseDouble(request.get("PrecioProducto").toString(
 * ))); prot.setProdTalla(request.get("TallaProducto").toString()); Categoria
 * Catt=CategoriaImplement.findById(Integer.parseInt(request.get("fkCategoria").
 * toString())); prot.setFkCategoria(Catt); proImpl.create(prot); Map<String,
 * Object> resp=new HashMap<>(); resp.put("msn", "Registro Exitoso");
 * resp.put("Status", HttpStatus.OK); return new ResponseEntity<>(resp,
 * HttpStatus.OK); }
 * 
 * @PostMapping("/update/{id}") public ResponseEntity<Map<String,Object>>
 * update(@RequestBody Map<String, Object> request,
 * 
 * @PathVariable int id){ Producto prot=new Producto(); prot.setId(0);
 * prot.setProdColor(request.get("ColorProducto").toString());
 * prot.setProdMarca(request.get("MarcaProducto").toString());
 * prot.setProdNombre(request.get("NombreProducto").toString());
 * prot.setProdPrecio(Double.parseDouble(request.get("PrecioProducto").toString(
 * ))); prot.setProdTalla(request.get("TallaProducto").toString()); Categoria
 * Catt=CategoriaImplement.findById(Integer.parseInt(request.get("fkCategoria").
 * toString())); prot.setFkCategoria(Catt); proImpl.create(prot); Map<String,
 * Object> resp=new HashMap<>(); resp.put("msn", "Actualizacion Exitosa");
 * resp.put("Status", HttpStatus.OK); return new ResponseEntity<>(resp,
 * HttpStatus.OK); }
 * 
 * @DeleteMapping("/delete/{id}") public ResponseEntity<Map<String,Object>>
 * delete(@RequestBody Map<String, Object> request,
 * 
 * @PathVariable int id){ Producto prot=new Producto(); prot.setId(0);
 * prot.setProdColor(request.get("ColorProducto").toString());
 * prot.setProdMarca(request.get("MarcaProducto").toString());
 * prot.setProdNombre(request.get("NombreProducto").toString());
 * prot.setProdPrecio(Double.parseDouble(request.get("PrecioProducto").toString(
 * ))); prot.setProdTalla(request.get("TallaProducto").toString());
 * proImpl.delete(prot); Map<String, Object> resp=new HashMap<>();
 * resp.put("msn", "Eliminacion Exitosa"); resp.put("Status", HttpStatus.OK);
 * return new ResponseEntity<>(resp, HttpStatus.OK); } }
 */
