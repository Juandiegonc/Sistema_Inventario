package com.web.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.web.model.ProductoEntity;
//import com.web.model.UsuarioEntity;
import com.web.service.ExcelExportService;
import com.web.service.ProductoService;



@Controller
public class ProductoController {
	
	@Autowired
	private ProductoService objproductoservice;
	
	@Autowired
    private ExcelExportService excelExportService;
	
	


	@GetMapping("/listarproductos")
	public String listarproductos(Model model) {
		model.addAttribute("objproducto", this.objproductoservice.listarProducto());
		return "listarproductos";
	}
	
	
	//VISTA REGISTRAR PRODUCTO
	@GetMapping("/frmproducto")
	public String frmregistrarproducto() {
	return "frmnuevoproducto";

	}
	
	@PostMapping("/registrarProducto")
	public String registrarProducto(@ModelAttribute ProductoEntity objproducto) {
	    try {
	        this.objproductoservice.registrarProducto(objproducto);
	        return "redirect:/listarproductos";
	    } catch(Exception ex) {
	        return ex.getMessage();
	    }
	}

	
	@GetMapping("/frmeditarproducto/{id}")
	public String frmeditarProducto(@PathVariable int id,Model model) {

		model.addAttribute("objcontrolador", this.objproductoservice.buscarProducto(id));
		
		return "frmeditarproducto";
	}

	@PostMapping("/editarProducto/{id}")
	public String editarProducto(@PathVariable int id, @ModelAttribute ProductoEntity objproducto, Model model) {
	    ProductoEntity objeditarproduct = this.objproductoservice.buscarProducto(id);
	    objeditarproduct.setSku(objproducto.getSku());
	    objeditarproduct.setNombrepro(objproducto.getNombrepro());
	    objeditarproduct.setCategoria(objproducto.getCategoria());
	    objeditarproduct.setDescripcion(objproducto.getDescripcion());
	    objeditarproduct.setNrocaja(objproducto.getNrocaja());
	    objeditarproduct.setCantidad(objproducto.getCantidad());
	    objeditarproduct.setTalla(objproducto.getTalla());
	    objeditarproduct.setPrecio(objproducto.getPrecio());

	    this.objproductoservice.editarProducto(objeditarproduct);
	    return "redirect:/listarproductos";
	}

	@GetMapping("/eliminarproducto/{id}")
	public String eliminarproducto(@PathVariable int id) {
	this.objproductoservice.eliminarProducto(id);
	return "redirect:/listarproductos";
	}
	
	 @GetMapping("/exportarProductosExcel")
	    public ResponseEntity<InputStreamResource> exportarProductosExcel() throws IOException {
	        List<ProductoEntity> productos = objproductoservice.listarProducto();
	        
	        ByteArrayInputStream bais = excelExportService.exportProductosToExcel(productos);
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Content-Disposition", "attachment; filename=productos.xlsx");
	        
	        return ResponseEntity
	                .ok()
	                .headers(headers)
	                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
	                .body(new InputStreamResource(bais));
	    }

	
}
