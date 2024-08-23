package com.web.controller;

import com.web.model.InventoryMovement;
import com.web.model.ProductoEntity;
import com.web.service.InventoryMovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/inventory-movements")
public class InventoryMovementController {
    @Autowired
    private InventoryMovementService inventoryMovementService;
    
    
    
    @GetMapping("/recent")
    public ResponseEntity<List<InventoryMovement>> getRecentMovements() {
        List<InventoryMovement> recentMovements = inventoryMovementService.getRecentMovements(10);
        System.out.println("Número de movimientos en el controlador: " + recentMovements.size());
        return ResponseEntity.ok(recentMovements);
    }
    
    @PostMapping
    public ResponseEntity<InventoryMovement> createMovement(@RequestBody InventoryMovement movement) {
        try {
            InventoryMovement savedMovement = inventoryMovementService.addMovement(movement);
            return ResponseEntity.ok(savedMovement);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}