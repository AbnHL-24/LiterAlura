package com.alura.LiterAlura.view;

import com.alura.LiterAlura.repository.BookRepository;
import com.alura.LiterAlura.service.ConsumoAPI;

import java.util.Locale;
import java.util.Scanner;

public class ConsoleMenu {
    private Scanner teclado = new Scanner(System.in).useLocale(Locale.US);
    private ConsumoAPI consumoAPI = ConsumoAPI.getInstance();

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
        System.out.println("Se debio imprimir el json.");
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
}
