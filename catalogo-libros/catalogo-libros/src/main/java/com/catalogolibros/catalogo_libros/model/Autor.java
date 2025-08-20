package com.catalogolibros.catalogo_libros.model;



import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private LocalDate fechaNacimiento;
    private LocalDate fechaFallecimiento;

    // getters y setters
}
