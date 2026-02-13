package com.example.biblioteca.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;      

@Entity
@Table(name = "libros")

public class Libro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "El autor es obligatorio")
	private String autor;
	
	@NotBlank(message = "El titulo del libro es obligatorio")
	private String titulo;
	
	private String descripcion;
	
	@NotBlank(message = "El género del libro es obligatorio")
	private String genero;
	
	@Min(value = 0, message = "El stock no puede ser negativo")
	private int stock;
	
	public long getId() {
		
		return id;
	}
	
	public void setId(long id) {
		
		this.id = id;
	}
	
	public String getAutor() {
		
		return autor;
	}
	
	public void setAutor(String autor) {
		
		this.autor = autor;
	}
	
	public String getTitulo() {
		
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		
		this.titulo = titulo;
	}
	
	public String getDescripcion() {
		
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		
		this.descripcion = descripcion;
	}
	
	public String getGenero() {
		
		return genero;
	}
	
	public void setGenero(String genero) {
		
		this.genero = genero;
	}
	
	public int getStock() {
		
		return stock;
	}
	
	public void setStock(int stock) {
		
		this.stock = stock;
	}
}
