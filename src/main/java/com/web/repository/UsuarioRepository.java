package com.web.repository;

//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.web.model.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

    // Definir método para buscar usuario por user y password
    UsuarioEntity findByUserAndPassword(String user, String password);
    
    UsuarioEntity findTopByOrderByIduserDesc();
    
    boolean existsByUser(String user);
    
    
    
    @Modifying
    @Query("UPDATE UsuarioEntity u SET u.habilitado = :habilitado WHERE u.iduser = :id")
    void actualizarEstadoHabilitacion(@Param("id") int id, @Param("habilitado") boolean habilitado);
    
    UsuarioEntity findByUser(String user); // Nuevo método
}




