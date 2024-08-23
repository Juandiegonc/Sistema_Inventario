package com.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.web.model.UsuarioEntity;
import com.web.service.UsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";  // Cambia "/login" a "login"
    };
    
    
    @PostMapping("/login")
    public String login(@ModelAttribute("UsuarioEntity") UsuarioEntity usuario, Model model, HttpSession session) {
        String user = usuario.getUser();
        String password = usuario.getPassword();
        
        UsuarioEntity usuarioEncontrado = usuarioService.findByUser(user);
        if (usuarioEncontrado != null) {
            if (!usuarioEncontrado.isHabilitado()) {
                model.addAttribute("error", "Su cuenta está deshabilitada. Por favor, contacte al administrador.");
                return "/login";
            }
            
            UsuarioEntity usuarioAutenticado = usuarioService.findByUserAndPassword(user, password);
            if (usuarioAutenticado != null) {
                session.setAttribute("usuario", usuarioAutenticado);
                session.setAttribute("rol", usuarioAutenticado.getRol());
                return "redirect:/index";
            } else {
                model.addAttribute("error", "Usuario o contraseña incorrectos");
                return "/login";
            }
        } else {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "/login";
        }
    }
}