package org.ies.tierno.library.components;

import org.ies.tierno.library.exceptions.BookNotFoundException;
import org.ies.tierno.library.exceptions.MemberNotFoundException;
import org.ies.tierno.library.model.Book;
import org.ies.tierno.library.model.Library;
import org.ies.tierno.library.model.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LibraryApp {
    private static final Logger log = LoggerFactory.getLogger(LibraryApp.class);

    private final Scanner scanner;

    private final Reader<Library> libraryReader;

    public LibraryApp(Scanner scanner, Reader<Library> libraryReader) {
        this.scanner = scanner;
        this.libraryReader = libraryReader;
    }

    public void run() {
        log.info("Bienvenido a la app de la biblioteca");
        Library library = libraryReader.read();

        int opt = 0;
        do {
            opt = chooseOption();
            if(opt == 1) {
                runFindBook(library);
            } else if(opt == 2) {
                runFindMember(library);
            } else if(opt == 3) {
                runExistBookLend(library);
            } else if (opt == 4) {
                log.info("Saliendo");
            } else {
                log.info("Opción inválida...");
            }
        } while (opt != 4);
    }

    private void runExistBookLend(Library library) {
        try {
            log.info("Introduce el NIF");
            String nif = scanner.nextLine();
            log.info("Introduce el ISBN");
            String isbn = scanner.nextLine();
            if(library.existLend(isbn, nif)) {
                log.info("Préstamo encontrado");
            } else {
                log.info("Préstamo no eccontrado");
            }
        } catch (MemberNotFoundException e) {
            log.info("No se ha encontrado el socio con NIF " + e.getNif());
        } catch (BookNotFoundException e) {
            log.info("No se ha encontrao el libro " + e.getIsbn());
        }
    }

    private void runFindMember(Library library) {
        try {
            log.info("Introduce el NIF");
            String nif = scanner.nextLine();
            Member member = library.findMember(nif);
            log.info(member.toString());
        } catch (MemberNotFoundException e) {
            log.info("No se ha encontrado el socio con NIF " + e.getNif());
        }
    }

    private void runFindBook(Library library) {
        try {
            log.info("Introduce el ISBN");
            String isbn = scanner.nextLine();
            Book book = library.findBook(isbn);
            log.info(book.toString());
        } catch (BookNotFoundException e) {
            log.info("No se ha encontrao el libro " + e.getIsbn());
        }
    }

    private int chooseOption() {
        Integer opt = null;
        do {
            try {
                log.info("Elige una opción:");
                log.info("1. Buscar libro");
                log.info("2. Buscar socio");
                log.info("3. Existe préstamo");
                log.info("4. Salir");

                opt = scanner.nextInt();
            } catch (InputMismatchException e) {
                log.error("Valor inválido, debe ser un número entre 1 y 4");
            } finally {
                scanner.nextLine();
            }
        } while (opt == null);

        return opt;
    }
}
