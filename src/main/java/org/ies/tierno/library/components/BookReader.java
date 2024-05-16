package org.ies.tierno.library.components;

import lombok.AllArgsConstructor;
import org.ies.tierno.library.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
public class BookReader implements Reader<Book> {
    private static final Logger log = LoggerFactory.getLogger(BookReader.class);
    private final Scanner scanner;

    @Override
    public Book read() {
        log.info("Inroduce los datos del libro");
        log.info("ISBN:");
        String isbn = scanner.nextLine();
        log.info("Título:");
        String title = scanner.nextLine();
        log.info("Autor:");
        String author = scanner.nextLine();

        int numGenres = readNumGenres();

        List<String> genres = new ArrayList<>();
        for (int i = 0; i < numGenres; i++) {
            log.info("Género " + i +":");
            genres.add(scanner.nextLine());
        }

        return new Book(isbn, title, author, genres);
    }

    private int readNumGenres() {
        log.info("¿Cuántos géneros tiene el libro?");
        int numGenres = -1;
        while(numGenres < 0) {
            try {
                numGenres = scanner.nextInt();
                if(numGenres < 0) {
                    log.info("Valor inválido, debe ser un número entero positivo");
                }
            } catch (InputMismatchException e) {
                log.error("Valor inválido, debe ser un número entero positivo");
            } finally {
                scanner.nextLine();
            }
        }

        return numGenres;
    }
}
