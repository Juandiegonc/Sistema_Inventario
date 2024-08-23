package com.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import com.web.model.ProductoEntity;

import com.web.model.InventoryMovement;
import com.web.model.ProductoEntity;
import com.web.service.InventoryMovementService;
import com.web.service.ProductoService;
import com.web.service.UsuarioService;

//import java.util.List;
//import java.util.Map;
//import java.util.Comparator;
//import java.util.HashMap;


@Controller
public class HomeController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private InventoryMovementService inventoryMovementService;

    
    @GetMapping("/")
    public String redirectToLogin() {
        return "redirect:/login";
    }

    @GetMapping("/index")
    public String home(Model model) {
        model.addAttribute("totalProductos", productoService.getTotalProductos());
        int productosConBajoStock = productoService.getProductosConBajoStock();
        model.addAttribute("productosConBajoStock", productosConBajoStock);
        model.addAttribute("ultimosProductos", productoService.getUltimosProductos());
        model.addAttribute("totalUsuarios", usuarioService.getTotalUsuarios());
        List<InventoryMovement> movimientosRecientes = inventoryMovementService.getRecentMovements(10);// Obtén los 10 movimientos más recientes
        List<ProductoEntity> productos = productoService.listarProducto();
        model.addAttribute("movimientosRecientes", movimientosRecientes);
        
        model.addAttribute("productos", productos);
        //ProductoEntity productoEstrella = MovimientoInventarioService.calcularProductoEstrella();
        //model.addAttribute("productoEstrella", productoEstrella);
        model.addAttribute("totalPorCategoria", productoService.getTotalPorCategoria());
        return "index";
    }
    
    
  


}