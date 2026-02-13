package com.example.biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.biblioteca.model.Libro;
import com.example.biblioteca.model.Prestamo;
import com.example.biblioteca.repository.PrestamoRepository;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
@CrossOrigin(origins = "*") 
public class PrestamoController {

    @Autowired
    private PrestamoRepository prestamoRepository;
    
    @Autowired
    private com.example.biblioteca.repository.LibroRepository libroRepository;

    // 1. LISTAR TODOS LOS LIBROS PRESTADOS
    @GetMapping
    public List<Prestamo> listarTodos() {
    	
        return prestamoRepository.findAll();
    }
    
    
    // 2. BORRA UN PRESTAMO POR ID DE PRESTAMO
    @DeleteMapping("/{id}")
    public void eliminarPrestamo(@PathVariable Long id) {
        prestamoRepository.deleteById(id);
    }

    
    // 3. BUSCAR POR NOMBRE DE SOCIO
    @GetMapping("/socio/{nombreSocio}")
    public List<Prestamo> buscarSocio(@PathVariable String nombreSocio) {
    	
        return prestamoRepository.findByNombreSocioContainingIgnoreCase(nombreSocio);
    }

    
    // 4. BUSCAR LIBROS VENCIDOS EN PRESTAMO
    @GetMapping("/vencidos")
    public List<Prestamo> listarVencidos() {
    	
        return prestamoRepository.findByDevueltoFalseAndFechaDevolucionMaximaBefore(LocalDate.now());
    }

    
    // 5. BUSCAR LIBROS NO DEVUELTOS (SIGUEN EN PRESTAMO)
    @GetMapping("/activos")
    public List<Prestamo> listarActivos() {
    	
        return prestamoRepository.findByDevuelto(false);
    }

    
    // 6. BUSCAR LIBROS YA DEVUELTOS (NO ESTAN EN PRESTAMO)
    @GetMapping("/completados")
    public List<Prestamo> listarDevueltos() {
    	
        return prestamoRepository.findByDevuelto(true);
    }

    
    // 7. HISTORIAL DE UN LIBRE MEDIANTE SU ID
    @GetMapping("/libro/{id}")
    public List<Prestamo> historialLibro(@PathVariable Long id) {
    	
        return prestamoRepository.findByLibroId(id);
    }
    
    
    // 8. REGISTRAR UN NUEVO PRÉSTAMO
    @PostMapping
    public Prestamo crearPrestamo(@RequestBody Prestamo prestamo) {
        
        Libro libro = libroRepository.findById(prestamo.getLibro().getId()).orElse(null);

        if (libro != null && libro.getStock() > 0) {
         
            libro.setStock(libro.getStock() - 1);
            libroRepository.save(libro); 
            
            return prestamoRepository.save(prestamo);
        } 
        
        else {
            
            throw new RuntimeException("No hay stock disponible para este libro");
        }
    }
    
    
    // 9. MARCAR COMO DEVUELTO
    @PutMapping("/{id}/devolucion")
    public Prestamo marcarComoDevuelto(@PathVariable Long id) {
       
        Prestamo p = prestamoRepository.findById(id).orElse(null);
        
        if (p != null) {
        	
            p.setDevuelto(true);
            return prestamoRepository.save(p);
        }
        
        return null;
    }
}
