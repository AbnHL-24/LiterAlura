package com.alura.LiterAlura;

import com.alura.LiterAlura.repository.BookRepository;
import com.alura.LiterAlura.view.ConsoleMenu;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ConsoleMenu consoleMenu = new ConsoleMenu();
		consoleMenu.muestraElMenu();
	}
}
