package com.example.biblioteca.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "prestamos")

public class Prestamo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "libro_id", nullable = false)
	private Libro libro;

	@Column(nullable = false)
	private String nombreSocio;

	@Column(nullable = false)
	private LocalDate fechaInicio;

	@Column(nullable = false)
	private LocalDate fechaDevolucionMaxima;

	private boolean devuelto = false;
	
	public Prestamo () {
		
	}

	public Prestamo(Libro libro, String nombreSocio, LocalDate fechaInicio, int diasPrestamo) {

		this.libro = libro;
		this.nombreSocio = nombreSocio;
		this.fechaInicio = fechaInicio;
		this.fechaDevolucionMaxima = fechaInicio.plusDays(diasPrestamo);
		this.devuelto = false;
	}

	public Long getId() {
		
		return id;
	}

	public void setId(Long id) {
		
		this.id = id;
	}

	public Libro getLibro() {
		
		return libro;
	}

	public void setLibro(Libro libro) {
		
		this.libro = libro;
	}

	public String getNombreSocio() {
		
		return nombreSocio;
	}

	public void setNombreSocio(String nombreSocio) {
		
		this.nombreSocio = nombreSocio;
	}

	public LocalDate getFechaInicio() {
		
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaDevolucionMaxima() {
		
		return fechaDevolucionMaxima;
	}

	public void setFechaDevolucionMaxima(LocalDate fechaDevolucionMaxima) {
		
		this.fechaDevolucionMaxima = fechaDevolucionMaxima;
	}

	public boolean isDevuelto() {
		
		return devuelto;
	}

	public void setDevuelto(boolean devuelto) {
		
		this.devuelto = devuelto;
	}
}
