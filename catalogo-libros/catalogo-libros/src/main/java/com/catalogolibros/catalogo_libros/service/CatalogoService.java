package com.catalogolibros.catalogo_libros.service;


import com.catalogolibros.model.Libro;
import com.catalogolibros.model.Autor;
import com.catalogolibros.repository.LibroRepository;
import com.catalogolibros.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CatalogoService {
    private final LibroRepository libroRepo;
    private final AutorRepository autorRepo;

    public CatalogoService(LibroRepository libroRepo, AutorRepository autorRepo) {
        this.libroRepo = libroRepo;
        this.autorRepo = autorRepo;
    }

    public Optional<Libro> buscarLibroPorTitulo(String titulo) {
        return libroRepo.findByTituloContainingIgnoreCase(titulo).stream().findFirst();
    }

    public List<Libro> listarLibros() {
        return libroRepo.findAll();
    }

    public List<Autor> listarAutores() {
        return autorRepo.findAll();
    }

    public List<Autor> listarAutoresVivosEnAnio(int anio) {
        LocalDate inicio = LocalDate.of(anio, 1, 1);
        LocalDate fin = LocalDate.of(anio, 12, 31);
        return autorRepo.findByFechaNacimientoBetween(inicio, fin);
    }

    public List<Libro> listarLibrosPorIdioma(String idioma) {
        return libroRepo.findByIdiomaIgnoreCase(idioma);
    }

    public void agregarLibro(Libro libro) {
        if (libroRepo.existsByTitulo(libro.getTitulo())) {
            throw new IllegalArgumentException("El libro ya fue registrado anteriormente.");
        }
        libroRepo.save(libro);
    }
}
