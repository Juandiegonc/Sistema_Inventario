package com.web.repository;

import com.web.model.InventoryMovement;
import com.web.model.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface InventoryMovementRepository extends JpaRepository<InventoryMovement, Long> {
    List<InventoryMovement> findByDateBetween(LocalDateTime start, LocalDateTime end);

    @Query("SELECT im.producto, SUM(im.quantity) as total FROM InventoryMovement im WHERE im.type = 'salida' GROUP BY im.producto ORDER BY total DESC")
    List<Object[]> findTopSellingProducts(Pageable pageable);
    
    @Query("SELECT m FROM InventoryMovement m ORDER BY m.date DESC")
    List<InventoryMovement> findTopNByOrderByDateDesc(Pageable pageable);

    default List<InventoryMovement> findTopNByOrderByDateDesc(int n) {
        return findTopNByOrderByDateDesc(PageRequest.of(0, n));
    }
    List<InventoryMovement> findAllByOrderByDateDesc();
    
    
}