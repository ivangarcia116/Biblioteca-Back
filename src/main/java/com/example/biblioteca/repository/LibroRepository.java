package com.example.biblioteca.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.biblioteca.model.Libro;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
		
	List<Libro> findByAutor(String autor);
	
	List<Libro> findByTitulo(String titulo);
	
	List<Libro> findByGenero(String genero);
	
	List<Libro> findByStock(int stock);
	
	List<Libro> findByDescripcionContainingIgnoreCase(String palabra);
}