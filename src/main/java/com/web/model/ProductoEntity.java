package com.web.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "producto")
public class ProductoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int idprenda;
	
	
	@Column
	private String sku;
	
	@Column
	private String nombrepro;
	
	@Column
	private String categoria;
	
	@Column
	private String descripcion;

	
	@Column
	private String nrocaja;
	
	@Column
	private int cantidad;
	
	@Column
	private String talla;
	
	@Column
	private double precio;
	
	@Column
	private Date fechaemision;

	public int getIdprenda() {
		return idprenda;
	}

	public void setIdprenda(int idprenda) {
		this.idprenda = idprenda;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getNombrepro() {
		return nombrepro;
	}

	public void setNombrepro(String nombrepro) {
		this.nombrepro = nombrepro;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNrocaja() {
		return nrocaja;
	}

	public void setNrocaja(String nrocaja) {
		this.nrocaja = nrocaja;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getTalla() {
		return talla;
	}

	public void setTalla(String talla) {
		this.talla = talla;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Date getFechaemision() {
		return fechaemision;
	}

	public void setFechaemision(Date fechaemision) {
		this.fechaemision = fechaemision;
	}
	
	

}

		

	
	
	
	
	
	
	

