package com.web.service;

import java.util.List;
import java.util.Map;

import com.web.model.ProductoEntity;

public interface ProductoService {

    // Métodos existentes
    public List<ProductoEntity> listarProducto();
    public ProductoEntity registrarProducto(ProductoEntity producto);
    public ProductoEntity editarProducto(ProductoEntity producto);
    public ProductoEntity buscarProducto(int id);
    public void eliminarProducto(int id);

    // Nuevos métodos para el dashboard
    public int getTotalProductos();
    public int getProductosConBajoStock();
    public List<ProductoEntity> getUltimosProductos();
    // public Map<String, Integer> getProductosPorCategoria();

    // Nuevo método para el gráfico de barras
    public List<Map<String, Object>> getTotalPorCategoria();
    
    
}

