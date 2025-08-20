package com.catalogolibros.catalogo_libros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
//public class CatalogoLibrosApplication {

	//public static void main(String[] args) {
		//SpringApplication.run(CatalogoLibrosApplication.class, args);
	//}

//}


import com.catalogolibros.model.Libro;
import com.catalogolibros.model.Autor;
import com.catalogolibros.service.CatalogoService;
import com.catalogolibros.service.GutendexService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class CatalogoLibrosApplication implements CommandLineRunner {
	@Autowired
	private CatalogoService catalogoService;
	@Autowired
	private GutendexService gutendexService;

	public static void main(String[] args) {
		SpringApplication.run(CatalogoLibrosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("\nOpciones:");
			System.out.println("1. Buscar libro (local o Gutendex)");
			System.out.println("2. Listar libros");
			System.out.println("3. Listar autores");
			System.out.println("4. Listar autores vivos en un año");
			System.out.println("5. Listar libros por idioma");
			System.out.println("6. Agregar libro");
			System.out.println("7. Salir");

			String opcion = scanner.nextLine();
			switch (opcion) {
				case "1":
					System.out.print("Título a buscar: ");
					String titulo = scanner.nextLine();
					catalogoService.buscarLibroPorTitulo(titulo)
							.ifPresentOrElse(
									libro -> System.out.println("Encontrado local: " + libro.getTitulo()),
									() -> System.out.println("No encontrado local. Buscando en Gutendex...")
							);
					var resp = gutendexService.buscarLibroPorTitulo(titulo);
					System.out.println("Resultado Gutendex: " + resp.toPrettyString());
					break;
				case "2":
					catalogoService.listarLibros().forEach(l -> System.out.println(l.getTitulo()));
					break;
				case "3":
					catalogoService.listarAutores().forEach(a -> System.out.println(a.getNombre()));
					break;
				case "4":
					System.out.print("Ingrese año: ");
					int anio = Integer.parseInt(scanner.nextLine());
					catalogoService.listarAutoresVivosEnAnio(anio)
							.forEach(a -> System.out.println(a.getNombre()));
					break;
				case "5":
					System.out.print("Ingrese idioma (ES/EN/FR/PT): ");
					String idioma = scanner.nextLine();
					catalogoService.listarLibrosPorIdioma(idioma).forEach(l -> System.out.println(l.getTitulo()));
					break;
				case "6":
					System.out.print("Título: ");
					String t = scanner.nextLine();
					System.out.print("Idioma: ");
					String i = scanner.nextLine();
					System.out.print("Año publicación: ");
					int año = Integer.parseInt(scanner.nextLine());
					// Autor simplificado:
					Autor autor = new Autor();
					autor.setNombre("Autor Desconocido");
					Libro nuevo = new Libro();
					nuevo.setTitulo(t);
					nuevo.setIdioma(i);
					nuevo.setAnioPublicacion(año);
					nuevo.setAutor(autor);
					catalogoService.agregarLibro(nuevo);
					System.out.println("Libro agregado.");
					break;
				case "7":
					System.out.println("Adiós.");
					System.exit(0);
				default:
					System.out.println("Opción inválida.");
			}
		}
	}
}
