package org.ies.tierno.library.components;

import org.ies.tierno.library.model.Book;
import org.ies.tierno.library.model.BookLend;
import org.ies.tierno.library.model.Library;
import org.ies.tierno.library.model.Member;
import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LibraryReaderTests {

    private Scanner scanner = mock(Scanner.class);

    private Reader<Book> bookReader = mock(Reader.class);

    private Reader<Member> memberReader = mock(Reader.class);

    private Reader<BookLend> bookLendReader = mock(Reader.class);

    private LibraryReader libraryReader = new LibraryReader(scanner, bookReader, memberReader, bookLendReader);

    @Test
    public void readLibraryTest() {
        Date currentDate = new Date();

        Library expectedLibrary = new Library(
                "IES Tierno",
                List.of(
                        new Book("001", "Prog", "Bob", List.of("Prog"))
                ),
                List.of(
                        new Member("1", "Bob", "Esponja", 1, 3)
                ),
                List.of(
                        new BookLend("001", "1", currentDate, currentDate)
                )
        );
        when(scanner.nextLine())
                .thenReturn(expectedLibrary.getName())
                .thenReturn("")
                .thenReturn("")
                .thenReturn("");
        when(scanner.nextInt())
                .thenReturn(expectedLibrary.getBooks().size())
                .thenReturn(expectedLibrary.getMembers().size())
                .thenReturn(expectedLibrary.getBookLends().size());

        when(bookReader.read())
                .thenReturn(new Book("001", "Prog", "Bob", List.of("Prog")));

        when(memberReader.read())
                .thenReturn(new Member("1", "Bob", "Esponja", 1, 3));

        when(bookLendReader.read())
                .thenReturn(new BookLend("001", "1", currentDate, currentDate));

        Library library = libraryReader.read();

        Assert.assertEquals(expectedLibrary, library);
    }
}
