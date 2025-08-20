package com.catalogolibros.catalogo_libros.model;


import jakarta.persistence.*;

@Entity
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String idioma;
    private Integer anioPublicacion;

    @ManyToOne
    private Autor autor;

    // getters y setters
}
