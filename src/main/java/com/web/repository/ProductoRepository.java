package com.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.web.model.ProductoEntity;
//import com.web.model.UsuarioEntity;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity, Integer>  {
	 List<ProductoEntity> findByNombrepro(String nombrepro);
	 
	 List<ProductoEntity> findByCantidadLessThan(int cantidad);
	    
	 @Query("SELECT p.categoria, COUNT(p) AS totalProductos, SUM(p.cantidad) AS totalUnidades FROM ProductoEntity p GROUP BY p.categoria")
	    List<Object[]> getTotalPorCategoria();


	    
	   // List<Object[]> countProductosByCategoria();
	    
	    int countByCantidadLessThan(int umbral);
}


