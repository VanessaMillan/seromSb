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

import com.SeromSb.dajuva.app.demodajuva.impl.PedidoImplement;
import com.SeromSb.dajuva.app.demodajuva.impl.UsuarioImplement;
import com.SeromSb.dajuva.app.demodajuva.impl.VentaImplement;
import com.SeromSb.dajuva.app.demodajuva.modelo.Pedido;
import com.SeromSb.dajuva.app.demodajuva.modelo.Usuario;
import com.SeromSb.dajuva.app.demodajuva.modelo.Venta;
import com.SeromSb.dajuva.app.demodajuva.service.UsuarioFacade;

@Controller
@RequestMapping(path = "/app/demodajuva/Pedido",method = {RequestMethod.PUT,RequestMethod.POST,RequestMethod.GET})
public class PedidoController {
	@Autowired
	private PedidoImplement PedImpl;
	@Autowired
	private UsuarioImplement UsuImpl;
	@Autowired
	private VentaImplement VenImp;
	@Autowired
	private UsuarioFacade usuarioFacade; 

	@GetMapping("/allPedido")

	public String listPedido(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		Usuario user = usuarioFacade.findByCorreo(userDetails.getUsername()); 
		
		model.addAttribute("pedidos", this.PedImpl.findALL());
		model.addAttribute("user", user);
		return "/pedido/listPedido";
	}

	@GetMapping("/createPedido")

	public String createFormPedido(Model model) {
		Pedido ped = new Pedido();
		List<Usuario> usuario = UsuImpl.findALL();
		List<Venta> venta = VenImp.findALL();

		model.addAttribute("pedido", ped);
		model.addAttribute("usuario", usuario);
		model.addAttribute("venta", venta); 
		return "/pedido/createPedido";

	}

	@GetMapping("/updatePedido/{id}")

	public String updateFormPedido(@PathVariable int id, Model model) {

		Pedido ped  = this.PedImpl.findbyId(id);
		List<Usuario> usuario = UsuImpl.findALL();
		List<Venta> venta = VenImp.findALL();

		model.addAttribute("pedido", ped);
		model.addAttribute("usuario", usuario);
		model.addAttribute("venta", venta); 

		return "pedido/editPedido";
	}

	@PostMapping("/savePedido")

	public String saveFormPedido(@ModelAttribute Pedido ped) {

		Usuario usu = this.UsuImpl.findbyId(ped.getFk_IdUsu().getId());
		Venta vent = this.VenImp.findbyId(ped.getFkventa().getId()); 
		
		ped.setFk_IdUsu(usu);
		ped.setFkventa(vent); 
		this.PedImpl.update(ped);
		return "redirect:/app/demodajuva/Pedido/allPedido";

	}
	
	@PostMapping("/pedido/{id}")
	public String updatePedido(@PathVariable int id, @ModelAttribute Pedido pedido) {
		Pedido pedidoOld= this.PedImpl.findbyId(id);

		pedidoOld.setId_pedido(id);
		pedidoOld.setPed_FechaEnt(pedido.getPed_FechaEnt());
		pedidoOld.setPed_FechaPed(pedido.getPed_FechaPed());
		pedidoOld.setFk_IdUsu(pedido.getFk_IdUsu());
		pedidoOld.setEstado(null);
		this.PedImpl.update(pedidoOld);
		return "redirect:/app/demodajuva/Pedido/allPedido";
	}

	@PostMapping("/deletePedido/{id}")

	public String deleteFormPedido(@PathVariable int id) {
		Pedido ped  = this.PedImpl.findbyId(id);
		String estadoActual = ped.getEstado();
		String Cambiarestado = estadoActual.equals("Activado")? "Desactivado": "Activado";
		ped.setEstado(Cambiarestado);
		this.PedImpl.update(ped);
		return "redirect:/app/demodajuva/Pedido/allPedido";
	}
}

/*public class PedidoController {
	@Autowired
	private PedidoImplement pedImpl;
	@PostMapping("/create")
	public ResponseEntity<Map<String,Object>> add(@RequestBody Map<String, Object> request){
		Pedido Pedi=new Pedido();
		Pedi.setId_pedido(0);
		Pedi.setPed_FechaPed(Utility.convertiFecha(request.get("FechaPedido").toString()));
		Pedi.setPed_FechaEnt(Utility.convertiFecha(request.get("FechaEntrega").toString()));
		Venta vent = VentaImplement.findById(Integer.parseInt(request.get("FkVenta").toString()));
		Pedi.setVenta(vent);
		Usuario User = UsuarioImplement.findById(Integer.parseInt(request.get("fkUsuario").toString()));
		Pedi.setFk_IdUsu(User);
		pedImpl.create(Pedi);
		Map<String, Object> resp=new HashMap<>();
		resp.put("msn", "Registro Exitoso");
		resp.put("Status", HttpStatus.OK);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	
	}
	@PostMapping("/update/{id}")
	public ResponseEntity<Map<String,Object>> update(@RequestBody Map<String, Object> request,
			@PathVariable int id){
		Pedido Pedi=new Pedido();
		Pedi.setId_pedido(0);
		Pedi.setPed_FechaPed(Utility.convertiFecha(request.get("FechaPedido").toString()));
		Pedi.setPed_FechaEnt(Utility.convertiFecha(request.get("FechaEntrega").toString()));
		Venta vent = VentaImplement.findById(Integer.parseInt(request.get("FkVenta").toString()));
		Pedi.setVenta(vent);
		Usuario User = UsuarioImplement.findById(Integer.parseInt(request.get("fkUsuario").toString()));
		Pedi.setFk_IdUsu(User);
		pedImpl.update(Pedi);
		Map<String, Object> resp=new HashMap<>();
		resp.put("msn", "Actualizacion Exitosa");
		resp.put("Status", HttpStatus.OK);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String,Object>> delete(@RequestBody Map<String, Object> request,
			@PathVariable int id){
		Pedido Pedi=new Pedido();
		Pedi.setId_pedido(0);
		Pedi.setPed_FechaPed(Utility.convertiFecha(request.get("FechaPedido").toString()));
		Pedi.setPed_FechaEnt(Utility.convertiFecha(request.get("FechaEntrega").toString()));
		pedImpl.delete(Pedi);
		Map<String, Object> resp=new HashMap<>();
		resp.put("msn", "Eliminacion Exitosa");
		resp.put("Status", HttpStatus.OK);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	
	}
	
}*/
