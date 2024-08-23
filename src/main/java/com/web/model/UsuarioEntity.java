package com.web.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class UsuarioEntity {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int iduser;	



@Column
private String user;

@Column
private String password;

@Column
private String rol;


@Column
private String nombres;


@Column
private String apellidos;


@Column
private String dni;



@Column
private String celular;


@Column
private String email;


@Column
private boolean habilitado = true;


public int getIduser() {
	return iduser;
}


public void setIduser(int iduser) {
	this.iduser = iduser;
}


public String getUser() {
	return user;
}


public void setUser(String user) {
	this.user = user;
}


public String getPassword() {
	return password;
}


public void setPassword(String password) {
	this.password = password;
}


public String getRol() {
	return rol;
}


public void setRol(String rol) {
	this.rol = rol;
}


public String getNombres() {
	return nombres;
}


public void setNombres(String nombres) {
	this.nombres = nombres;
}


public String getApellidos() {
	return apellidos;
}


public void setApellidos(String apellidos) {
	this.apellidos = apellidos;
}


public String getDni() {
	return dni;
}


public void setDni(String dni) {
	this.dni = dni;
}


public String getCelular() {
	return celular;
}


public void setCelular(String celular) {
	this.celular = celular;
}


public String getEmail() {
	return email;
}


public void setEmail(String email) {
	this.email = email;
}


public boolean isHabilitado() {
	return habilitado;
}


public void setHabilitado(boolean habilitado) {
	this.habilitado = habilitado;
}






}




