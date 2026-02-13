package com.example.biblioteca.repository;

import com.example.biblioteca.model.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {

    List<Prestamo> findByNombreSocioContainingIgnoreCase(String nombreSocio);

    List<Prestamo> findByDevueltoFalseAndFechaDevolucionMaximaBefore(LocalDate fecha);
    
    List<Prestamo> findByNombreSocioAndDevueltoFalse(String nombreSocio);

    List<Prestamo> findByLibroId(Long libroId);

    List<Prestamo> findByDevuelto(boolean estado);

    List<Prestamo> findByFechaInicio(LocalDate fecha);
}