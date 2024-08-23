package com.web.Config;

//import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

//import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.Arrays;
import java.util.List;

@Component
public class RoleInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	    HttpSession session = request.getSession();
	    String rol = (String) session.getAttribute("rol");
	    String requestURI = request.getRequestURI();

	    // Permitir acceso a recursos estáticos sin importar el rol
	    if (requestURI.startsWith("/css") || requestURI.startsWith("/js") || requestURI.startsWith("/img") || 
	        requestURI.startsWith("/fonts") || requestURI.startsWith("/vendor")) {
	        return true;
	    }

	    if ("ADMINISTRA".equals(rol)) {
	        return true; // El administrador tiene acceso a todas las páginas
	    } else if ("EMPLEADO".equals(rol)) {
	        // Lista de URLs permitidas para empleados
	        List<String> allowedUrls = Arrays.asList("/index", "/listarusuario", "/listarproductos", "/api/inventory-movements");

	        if (allowedUrls.stream().anyMatch(requestURI::startsWith)) {
	            return true;
	        } else {
	            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
	            return false;
	        }
	    }

	    // Si no hay rol en la sesión
	    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	    return false;
	}

}
