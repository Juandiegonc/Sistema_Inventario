package com.web.controller;

import java.security.Principal;
//import java.util.HashMap;
//import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.model.UsuarioEntity;
import com.web.service.UsuarioService;

@Controller
public class UsuarioController {
	@Autowired
	 private UsuarioService objusuarioservice;
//--------------------------------------------------------------------------------------------------------------------------	
	@GetMapping("/listarusuario")
	public String listarusuario(Model model, Principal principal) {
	    if (principal != null) {
	        String user = principal.getName();
	        UsuarioEntity usuarioAutenticado = objusuarioservice.findByUser(user);
	        if (usuarioAutenticado != null) {
	            model.addAttribute("nombreUsuario", usuarioAutenticado.getUser());
	        } else {
	            model.addAttribute("nombreUsuario", "Invitado");
	        }
	    } else {
	        model.addAttribute("nombreUsuario", "Invitado");
	    }
	    model.addAttribute("objusuario", objusuarioservice.listarUsuario());
	    return "listarusuario";
	}
	
//-------------------------------------------------------------------------------------------------------------------------------	
//VISTA REGISTRAR USUARIO
@GetMapping("/frmusuario")
public String frmregistrarusuario() {
return "frmnuevousuario";

}

@PostMapping("/registrarUsuario")
public String registrarUsuario(@ModelAttribute("UsuarioEntity") UsuarioEntity objusuario) {
    try {
        this.objusuarioservice.registrarUsuario(objusuario);
        return "redirect:/listarusuario";
    } catch(Exception ex) {
        return ex.getMessage();
    }
}


//-------------------------------------------------------------------------------------------------------------------------------	
@GetMapping("/frmeditarusuario/{id}")
public String frmeditarUsuario(@PathVariable int id,Model model) {

	model.addAttribute("objcontrolador", this.objusuarioservice.buscarUsuario(id));
	
	return "frmeditarusuario";
}

@PostMapping("/editarUsuario/{id}")
public String editarUsuario(@PathVariable int id,@ModelAttribute("entidad") UsuarioEntity objusuario,Model model) {
	
	// Crear una instancia de la clase entidad y service
	UsuarioEntity objeditaruser=this.objusuarioservice.buscarUsuario(id);
	
	objeditaruser.setPassword(objusuario.getPassword());
	objeditaruser.setRol(objusuario.getRol());
	objeditaruser.setDni(objusuario.getDni());
	objeditaruser.setNombres(objusuario.getNombres());
	objeditaruser.setApellidos(objusuario.getApellidos());
	objeditaruser.setEmail(objusuario.getEmail());
	objeditaruser.setCelular(objusuario.getCelular());

	
	this.objusuarioservice.editarUsuario(objeditaruser);
	
	return "redirect:/listarusuario";
}

//-------------------------------------------------------------------------------------------------------------------------------	


//@GetMapping("/eliminar/{id}")
//public String eliminarUsuario(@PathVariable int id) {
//this.objusuarioservice.eliminarUsuario(id);
//return "redirect:/listarusuario";
//}


//-------------------------------------------------------------------------------------------------------------------------------	

@PostMapping("/cambiarEstadoUsuario/{id}")
public String cambiarEstadoUsuario(@PathVariable int id, @RequestParam(name = "habilitado", required = false) Boolean habilitado) {
    // Si habilitado es null, lo tomamos como false
    boolean nuevoEstado = (habilitado != null) ? habilitado : false;
    this.objusuarioservice.cambiarEstadoHabilitacion(id, nuevoEstado);
    return "redirect:/listarusuario";
}



}

