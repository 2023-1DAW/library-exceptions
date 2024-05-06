package org.ies.tierno.library.model;

import org.ies.tierno.library.exceptions.BookNotFoundException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class LibraryTests {

    private Library library = new Library(
            "IES Tierno Galván",
            List.of(
                    new Book("00001", "Programación en Java", "Bob Esponja", List.of("Informática", "Java")),
                    new Book("00002", "Programación en Kotlin", "Peppa Pig", List.of("Informática", "Kotlin"))
            ),
            List.of(
                    new Member("00001X", "Bob","Esponja", 1, 28000),
                    new Member("00002Y", "George","Pig", 2, 28000)
            ),
            List.of(
                    new BookLend("00001", "00001X", new Date(), new Date())
            )
    );

    @Test
    public void findBookTest() throws BookNotFoundException {
        Book book = library.findBook("00001");

        Assert.assertEquals(
                new Book("00001", "Programación en Java", "Bob Esponja", List.of("Informática", "Java")),
                book
        );
    }

    @Test(expected = BookNotFoundException.class)
    public void findBookNotFoundTest() throws BookNotFoundException {
        Book book = library.findBook("1");
    }
}
