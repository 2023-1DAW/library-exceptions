package org.ies.tierno.library.components;

import org.ies.tierno.library.model.Book;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class BookReaderTests {

    private Scanner mockedScanner = mock(Scanner.class);
    private BookReader bookReader = new BookReader(mockedScanner);

    @Test
    public void readBookTest() {
        Book expected = new Book(
                "0001",
                "Programación en Java",
                "Bob Esponja",
                List.of("Programación", "Java")
        );
        when(mockedScanner.nextLine())
                .thenReturn(expected.getIsbn())
                .thenReturn(expected.getTitle())
                .thenReturn(expected.getAuthor())
                .thenReturn("")
                .thenReturn("Programación")
                .thenReturn("Java");

        when(mockedScanner.nextInt()).thenReturn(expected.getGenres().size());

        Book book = bookReader.read();

        Assert.assertEquals(expected, book);
    }
}
