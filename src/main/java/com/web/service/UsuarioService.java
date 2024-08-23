package com.web.service;

import java.util.List;
import com.web.model.UsuarioEntity;

public interface UsuarioService {
    // Métodos existentes
    public List<UsuarioEntity> listarUsuario();
    public UsuarioEntity registrarUsuario(UsuarioEntity usuario);
    public UsuarioEntity editarUsuario(UsuarioEntity usuario);
    public UsuarioEntity buscarUsuario(int id);
    public void eliminarUsuario(int id);
    UsuarioEntity findByUserAndPassword(String user, String password);
    void cambiarEstadoHabilitacion(int id, boolean habilitado);
    public UsuarioEntity findByUser(String user);
    
    // Nuevo método para el dashboard
    public int getTotalUsuarios();
}