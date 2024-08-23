package com.web.service;

import com.web.model.InventoryMovement;
import com.web.model.ProductoEntity;
import com.web.repository.InventoryMovementRepository;
import com.web.repository.ProductoRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryMovementService {
    @Autowired
    private InventoryMovementRepository inventoryMovementRepository;

    
    @Autowired
    private ProductoRepository productoRepository;
    
    
    public List<InventoryMovement> getMovementsBetweenDates(LocalDateTime start, LocalDateTime end) {
        return inventoryMovementRepository.findByDateBetween(start, end);
    }

    public void addMovement(ProductoEntity producto, int quantity, String type) {
        InventoryMovement movement = new InventoryMovement();
        movement.setProducto(producto);
        movement.setQuantity(quantity);
        movement.setType(type);
        movement.setDate(LocalDateTime.now());
        inventoryMovementRepository.save(movement);
    }

    public List<ProductoEntity> getTopSellingProducts(int limit) {
        return inventoryMovementRepository.findTopSellingProducts(PageRequest.of(0, limit))
                .stream()
                .map(result -> (ProductoEntity) result[0])
                .collect(Collectors.toList());
    }
    
  
    public List<InventoryMovement> getRecentMovements(int limit) {
        List<InventoryMovement> movements = inventoryMovementRepository.findTopNByOrderByDateDesc(PageRequest.of(0, limit));
        System.out.println("Número de movimientos recuperados: " + movements.size());
        return movements;
    }
    @Transactional
    public InventoryMovement addMovement(InventoryMovement movement) {
        ProductoEntity producto = productoRepository.findById(movement.getProducto().getIdprenda())
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        
        movement.setProducto(producto);
        movement.setDate(LocalDateTime.now());
        
        // Actualizar el stock del producto
        if ("aumentar_stock".equals(movement.getType()) || "devolucion".equals(movement.getType())) {
            producto.setCantidad(producto.getCantidad() + movement.getQuantity());
        } else if ("venta".equals(movement.getType()) || "merma".equals(movement.getType())) {
            if (producto.getCantidad() < movement.getQuantity()) {
                throw new RuntimeException("Stock insuficiente");
            }
            producto.setCantidad(producto.getCantidad() - movement.getQuantity());
        }
        
        productoRepository.save(producto);
        return inventoryMovementRepository.save(movement);
    }
    
    public List<InventoryMovement> getAllMovements() {
        return inventoryMovementRepository.findAllByOrderByDateDesc();
    }
    
    
    
}