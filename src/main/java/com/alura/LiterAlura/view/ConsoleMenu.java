package com.alura.LiterAlura.view;

import com.alura.LiterAlura.model.api.BookAPI;
import com.alura.LiterAlura.model.api.ListOfBooksAPI;
import com.alura.LiterAlura.model.database.AuthorDB;
import com.alura.LiterAlura.model.database.BookDB;
import com.alura.LiterAlura.model.database.LibroAutorDB;
import com.alura.LiterAlura.repository.AuthorRepository;
import com.alura.LiterAlura.repository.BookRepository;
import com.alura.LiterAlura.repository.LibroAutorRepository;
import com.alura.LiterAlura.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Scanner;

@Component
public class ConsoleMenu {
    private Scanner teclado = new Scanner(System.in).useLocale(Locale.US);
    private ConsumoAPI consumoAPI = ConsumoAPI.getInstance();
    private ConvierteDatos convierteDatos = ConvierteDatos.getInstance();

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private LibroAutorRepository libroAutorRepository;

    @Autowired
    private AuthorService authorService;
    @Autowired
    private BookService bookService;
    @Autowired
    private LibroAutorService libroAutorService;


    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar libro por título.
                    2 - Listar libros registrados.
                    3 - Listar autores registrados.
                    4 - Listar autores vivos en un determinado año.
                    5 - Listar libros por idioma.
                    
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = leerOpcion();
            //teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibrosPorTitulo();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private void buscarLibrosPorTitulo() {
        System.out.println("Ingrese el nombre del título que desea buscar:");
        String libroIngresado = teclado.nextLine();
        String json = consumoAPI.obtenerLibros(libroIngresado);
        System.out.println(json);
        // Se mapea una lista de BooksAPI provenientes del json devuelto por la GutenDex.
        ListOfBooksAPI listOfBooksAPI = convierteDatos.obtenerDatos(json, ListOfBooksAPI.class);
        // Se obtiene el primer resultado, se crea y guarda un BookDB con él.
        BookDB bookDB = new BookDB(listOfBooksAPI.results().getFirst());
        var s = bookService.saveBook(bookDB);
        // Intentando guardar los LibroAutor y los Autores.
        listOfBooksAPI.results().getFirst().authors().stream()
                .forEach(a -> {
                    // Creando cada autor y guardándolo,
                    AuthorDB authorDB = new AuthorDB(a);
                    var aDB = authorService.saveAuthor(authorDB);
                    // Creando cada libroAutor y guardándolo.
                    LibroAutorDB libroAutorDB = new LibroAutorDB(s, aDB);
                    var laDB = libroAutorService.saveLibroAutor(libroAutorDB);
                });
        imprimirLibroGuardado(listOfBooksAPI.results().getFirst());
        System.out.println();

        // Se suspende lo de abajo porque ni yo sé qué hice.
        /*s.getLibroAutor().stream()
                .forEach(a -> {
                    libroAutorRepository.save(a);
                    authorRepositoory.save(a.getAuthorDB());
                });*/
    }

    private int leerOpcion() {
        while (true) {
            try {
                return Integer.parseInt(teclado.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Entrada no válida. Por favor ingrese un número: ");
            }
        }
    }

    private void imprimirLibroGuardado(BookAPI bookAPI) {
        System.out.println("Libro Guardado:");
        System.out.println("Titulo:");
        System.out.println(bookAPI.title());
        System.out.println("Autor(es):");
        bookAPI.authors().stream()
                .forEach(a -> System.out.println(a.name()));
    }
}
