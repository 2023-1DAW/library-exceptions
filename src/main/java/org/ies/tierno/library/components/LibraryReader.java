package org.ies.tierno.library.components;

import lombok.AllArgsConstructor;
import org.ies.tierno.library.model.Book;
import org.ies.tierno.library.model.BookLend;
import org.ies.tierno.library.model.Library;
import org.ies.tierno.library.model.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
public class LibraryReader implements Reader<Library> {
    private static final Logger log = LoggerFactory.getLogger(LibraryReader.class);
    private final Scanner scanner;
    private final Reader<Book> bookReader;
    private final Reader<Member> memberReader;
    private final Reader<BookLend> bookLendReader;

    @Override
    public Library read() {
        log.info("Introduce los datos de la biblioteca");
        log.info("Nombre:");
        String name = scanner.nextLine();
        List<Book> books = askItems("¿Cuántos libros hay?", bookReader);
        List<Member> members = askItems("¿Cuántos socios hay?", memberReader);
        List<BookLend> bookLends = askItems("¿Cuántos préstamos hay?", bookLendReader);
        return new Library(name, books, members, bookLends);
    }

    private <T> List<T> askItems(String message, Reader<T> reader) {
        int size = readNumElements(message);
        List<T> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(reader.read());
        }
        return list;
    }

    private int readNumElements(String message) {
        log.info(message);
        int numGenres = -1;
        while (numGenres < 0) {
            try {
                numGenres = scanner.nextInt();
                if (numGenres < 0) {
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
