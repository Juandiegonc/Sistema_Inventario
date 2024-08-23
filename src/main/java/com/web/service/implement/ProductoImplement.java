package com.web.service.implement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.web.model.ProductoEntity;
//import com.web.model.UsuarioEntity;
import com.web.repository.ProductoRepository;

import com.web.service.ProductoService;

@Service
public class ProductoImplement implements ProductoService{
	@Autowired
	private ProductoRepository repo;
	@Override
	public List<ProductoEntity> listarProducto() {
		// TODO Auto-generated method stub
		return (List<ProductoEntity>) repo.findAll();
	}

	@Override
	public ProductoEntity registrarProducto(ProductoEntity producto) {
	    // Verificar si ya existen productos con el mismo nombre
	    List<ProductoEntity> productosConMismoNombre = repo.findByNombrepro(producto.getNombrepro());

	    int secuencia = 1; // Inicializar secuencia

	    if (!productosConMismoNombre.isEmpty()) {
	        // Obtener el último producto registrado con el mismo nombre
	        ProductoEntity ultimoProducto = productosConMismoNombre.get(productosConMismoNombre.size() - 1);

	        // Extraer la secuencia del SKU del último producto
	        String ultimoSku = ultimoProducto.getSku();
	        String secuenciaStr = ultimoSku.replaceAll("[^0-9]", ""); // Solo los números
	        secuencia = Integer.parseInt(secuenciaStr) + 1; // Incrementar la secuencia
	    }

	    // Generar el SKU con las iniciales del nombre y la nueva secuencia
	    String nuevoSku = generarSKU(producto, secuencia);
	    producto.setSku(nuevoSku);

	    // Guardar el producto con el SKU generado
	    return this.repo.save(producto);
	}


	@Override
	public ProductoEntity editarProducto(ProductoEntity producto) {
		// TODO Auto-generated method stub
		return this.repo.save(producto);
	}

	@Override
	public ProductoEntity buscarProducto(int id) {
		// TODO Auto-generated method stub
		return this.repo.findById(id).get();
	}

	@Override
	public void eliminarProducto(int id) {
		this.repo.deleteById(id);
		
	}
	
	
	private String generarSKU(ProductoEntity producto, int secuencia) {
	    // Obtiene las iniciales del nombre del producto
	    String iniciales = obtenerIniciales(producto.getNombrepro());
	    
	    // Formatea la secuencia con 3 dígitos (puede ajustarse según sea necesario)
	    String numero = String.format("%03d", secuencia);
	    
	    // Combina las iniciales con la secuencia para formar el SKU
	    String sku = iniciales + numero;
	    
	    return sku;
	}

	private String obtenerIniciales(String nombrepro) {
	    StringBuilder iniciales = new StringBuilder();
	    if (nombrepro != null && !nombrepro.isEmpty()) {
	        String[] palabras = nombrepro.split("\\s+");
	        for (String palabra : palabras) {
	            iniciales.append(palabra.charAt(0));
	        }
	    }
	    return iniciales.toString().toUpperCase();
	}
	
	
	 @Override
	    public int getTotalProductos() {
	        return (int) repo.count();
	    }

	 @Override
	 public int getProductosConBajoStock() {
	     int umbralBajaCantidad = 3; // Define este valor según tus necesidades
	     return repo.countByCantidadLessThan(umbralBajaCantidad);
	 }

	    @Override
	    public List<ProductoEntity> getUltimosProductos() {
	        PageRequest pageRequest = PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "idprenda"));
	        return repo.findAll(pageRequest).getContent();
	    }

	   /* @Override
	    public Map<String, Integer> getProductosPorCategoria() {
	        List<Object[]> result = repo.countProductosByCategoria();
	        Map<String, Integer> productosPorCategoria = new HashMap<>();
	        for (Object[] row : result) {
	            productosPorCategoria.put((String) row[0], ((Long) row[1]).intValue());
	        }
	        return productosPorCategoria;
	    }*/
	    @Override
	    public List<Map<String, Object>> getTotalPorCategoria() {
	        List<Object[]> results = repo.getTotalPorCategoria();
	        List<Map<String, Object>> data = new ArrayList<>();
	        for (Object[] result : results) {
	            Map<String, Object> item = new HashMap<>();
	            item.put("categoria", result[0]);
	            item.put("totalProductos", result[1]);
	            item.put("totalUnidades", result[2]);
	            data.add(item);
	        }
	        return data;
	    }



}
