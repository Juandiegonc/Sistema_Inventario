package com.web.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.web.model.UsuarioEntity;
import com.web.repository.UsuarioRepository;
import com.web.service.UsuarioService;

import jakarta.transaction.Transactional;

@Service
public class UsuarioImplement implements UsuarioService {

    @Autowired
    private UsuarioRepository repo;

    @Autowired


	@Override
	public List<UsuarioEntity> listarUsuario() {
		return (List<UsuarioEntity>) repo.findAll();
	}

	@Override
	public UsuarioEntity registrarUsuario(UsuarioEntity usuario) {
		String nombreUsuario = generarNombreUsuario(usuario);
        usuario.setUser(nombreUsuario);
        return this.repo.save(usuario);
	}

	@Override
	public UsuarioEntity editarUsuario(UsuarioEntity usuario) {
		// TODO Auto-generated method stub
		return this.repo.save(usuario);
	}

	@Override
	public UsuarioEntity buscarUsuario(int id) {
		// TODO Auto-generated method stub
		return this.repo.findById(id).get();
	}

	@Override
	public void eliminarUsuario(int id) {
		this.repo.deleteById(id);
		
	}

	@Override
	public UsuarioEntity findByUserAndPassword(String user, String password) {
	    UsuarioEntity usuario = repo.findByUserAndPassword(user, password);
	    if (usuario != null && !usuario.isHabilitado()) {
	        return null; // Tratar a los usuarios deshabilitados como si no existieran
	    }
	    return usuario;
	}

	private String generarNombreUsuario(UsuarioEntity usuario) {
	    String baseUsuario = obtenerFormatoNombre(usuario.getNombres(), usuario.getApellidos(), usuario.getDni()).toUpperCase();
	    
	    // Verificar si el nombre de usuario ya existe y agregar un número si es necesario
	    String nombreUsuario = baseUsuario;
	    int contador = 1;
	    while (repo.existsByUser(nombreUsuario)) {
	        nombreUsuario = baseUsuario + contador;
	        contador++;
	    }
	    return nombreUsuario;
	}

	private String obtenerFormatoNombre(String nombres, String apellidos, String dni) {
	    StringBuilder resultado = new StringBuilder();

	    // Obtener el primer nombre
	    if (nombres != null && !nombres.isEmpty()) {
	        String[] nombresSeparados = nombres.split("\\s+");
	        if (nombresSeparados.length > 0) {
	            resultado.append(nombresSeparados[0]); // Primer nombre
	        }
	    }

	    // Obtener el primer apellido
	    if (apellidos != null && !apellidos.isEmpty()) {
	        String[] apellidosSeparados = apellidos.split("\\s+");
	        if (apellidosSeparados.length > 0) {
	            resultado.append(".").append(apellidosSeparados[0]); // Primer apellido
	        }
	    }

	    // Añadir los últimos 4 dígitos del DNI
	    if (dni != null && dni.length() >= 4) {
	        String ultimosCuatroDigitos = dni.substring(dni.length() - 4);
	        resultado.append(".").append(ultimosCuatroDigitos);
	    }

	    return resultado.toString();
	}

	 
	 
	 @Override
	    @Transactional
	    public void cambiarEstadoHabilitacion(int id, boolean habilitado) {
	        repo.actualizarEstadoHabilitacion(id, habilitado);
	    }
	 
	 
	    @Override
	    public UsuarioEntity findByUser(String usuario) {
	        return repo.findByUser(usuario); // Implementación del nuevo método
	    }
	    
	    @Override
	    public int getTotalUsuarios() {
	        return (int) repo.count();
	    }
	 
}
