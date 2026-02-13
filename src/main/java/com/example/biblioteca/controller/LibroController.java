package com.example.biblioteca.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.biblioteca.model.Libro;
import com.example.biblioteca.repository.LibroRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/libros")
@CrossOrigin(origins = "*") 

public class LibroController {

    @Autowired
    private LibroRepository libroRepository;

    
    // 1. LISTAR TODOS LOS LIBROS
    @GetMapping
    public List<Libro> listarTodos() {
    	
        return libroRepository.findAll();
    }
    
    // 2. ELIMINA UN LIBRO 
    @DeleteMapping("/{id}")
    public void eliminarLibro(@PathVariable Long id) {
        libroRepository.deleteById(id);
    }
    
    
    // 2. BUSCAR POR AUTOR
    @GetMapping("/autor/{autor}")
    public List<Libro> buscarPorAutor (@PathVariable String autor) {
    	
    	return libroRepository.findByAutor(autor);
    }
    
    
    // 3. BUSCAR POR TITULO
    @GetMapping("/titulo/{titulo}")
    public List<Libro> buscarPorTitulo (@PathVariable String titulo) {
    	
    	return libroRepository.findByTitulo(titulo);
    }
    
    
    // 4. BUSCAR POR GÉNERO 
    @GetMapping("/genero/{genero}")
    public List<Libro> buscarPorGenero(@PathVariable String genero) {
    	
        return libroRepository.findByGenero(genero);
    }
    
    
    // 5. BUSCAR POR PALABRA CLAVE DE LA DESCRIPCION DE UN LIBRO
    @GetMapping("/palabra/{palabra}")
    public List<Libro> buscarDescripcionPorPalabraClave(@PathVariable String palabra) {
    	
    	return libroRepository.findByDescripcionContainingIgnoreCase(palabra);
    	
    }
    
    // 6. DAR DE ALTA UN LIBRO 
    @PostMapping
    public ResponseEntity<?> crearLibro(@Valid @RequestBody Libro libro) {
    	
        try {
        	
            Libro nuevo = libroRepository.save(libro);
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } 
        
        catch (Exception e) {
        	
            return new ResponseEntity<>("Error al insertar el libro: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // 7. BUSCAR POR CANTIDAD DE STOCK EXACTA
    @GetMapping("/stock/{cantidad}")
    public List<Libro> buscarPorStock(@PathVariable int cantidad) {
        
        return libroRepository.findByStock(cantidad);
    }
}
