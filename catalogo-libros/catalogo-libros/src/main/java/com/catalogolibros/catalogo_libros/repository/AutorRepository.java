package com.catalogolibros.catalogo_libros.repository;

import com.catalogolibros.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    List<Autor> findAll();
   List<Autor> findByFechaFallecimientoIsNull();
    List<Autor> findByFechaNacimientoBetween(LocalDate inicio, LocalDate fin);
}
