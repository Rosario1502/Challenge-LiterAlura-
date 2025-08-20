package com.catalogolibros.catalogo_libros.repository;


import com.catalogolibros.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    List<Libro> findByTituloContainingIgnoreCase(String titulo);
    List<Libro> findByIdiomaIgnoreCase(String idioma);
    boolean existsByTitulo(String titulo);
}
