package com.alura.LiterAlura.view;

import com.alura.LiterAlura.model.api.BookAPI;
import com.alura.LiterAlura.model.api.ListOfBooksAPI;
import com.alura.LiterAlura.model.database.AuthorDB;
import com.alura.LiterAlura.model.database.BookDB;
import com.alura.LiterAlura.model.database.LibroAutorDB;
import com.alura.LiterAlura.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

@Component
public class ConsoleMenu {
    private Scanner teclado = new Scanner(System.in).useLocale(Locale.US);
    private ConsumoAPI consumoAPI = ConsumoAPI.getInstance();
    private ConvierteDatos convierteDatos = ConvierteDatos.getInstance();

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

            switch (opcion) {
                case 1:
                    buscarLibrosPorTitulo();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    autoresVivosEnDeterminadoányo();
                    break;
                case 5:
                    librosPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private void librosPorIdioma() {
        System.out.println("ingrese las iniciales de los idiomas:");
        System.out.println("es - español\nen - ingles\nfr - frances\npt - portugues");
        String idioma = teclado.next();
        List<BookDB> librosEnDeterminadoIdioma = bookService.listarLibrosPorIdioma(idioma);
        System.out.println("Libros en el idioma: " + librosEnDeterminadoIdioma);
        librosEnDeterminadoIdioma.stream()
                .forEach(b -> System.out.println(b.getTitle()));
    }

    private void autoresVivosEnDeterminadoányo() {
        System.out.println("Ingrese el nombre del autor que desea buscar: ");
        String autorIngresado = teclado.nextLine();
        List<AuthorDB> autoresVivosXAnyo = authorService.listarAutoresVivosEnAno(Integer.parseInt(autorIngresado));
        System.out.println("Autores vivos en el año: " + autoresVivosXAnyo + ".");
        autoresVivosXAnyo.stream()
                .forEach(a -> System.out.println(a.getName()));
        System.out.println();
    }

    private void listarAutoresRegistrados() {
        List<AuthorDB> autoresRegistrados = authorService.listarAutoresRegistrados();
        autoresRegistrados.stream()
                .forEach(a -> {
                    System.out.println("Autor guardado: ");
                    System.out.println(a.getName());
                    System.out.println();
                });
    }

    private void listarLibrosRegistrados() {
        List<BookDB> librosRegistrados = bookService.listarLibrosRegistrados();
        librosRegistrados.stream()
                .forEach(l -> imprimirLibroGuardadoDB(l));
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
        imprimirLibroGuardadoAPI(listOfBooksAPI.results().getFirst());
        System.out.println();
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

    private void imprimirLibroGuardadoAPI(BookAPI bookAPI) {
        System.out.println("Libro Guardado:");
        System.out.println("Titulo:");
        System.out.println(bookAPI.title());
        System.out.println("Autor(es):");
        bookAPI.authors().stream()
                .forEach(a -> System.out.println(a.name()));
    }

    private void imprimirLibroGuardadoDB(BookDB bookDB) {
        System.out.println("Libro Guardado:");
        System.out.println("Titulo:");
        System.out.println(bookDB.getTitle());
        System.out.println();
    }
}
