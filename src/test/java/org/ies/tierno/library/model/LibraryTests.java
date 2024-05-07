package org.ies.tierno.library.model;

import org.ies.tierno.library.exceptions.BookNotFoundException;
import org.ies.tierno.library.exceptions.MemberNotFoundException;
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
                    new Member("00001X", "Bob", "Esponja", 1, 28000),
                    new Member("00002Y", "George", "Pig", 2, 28000)
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

    @Test
    public void findMemberTest() throws MemberNotFoundException {
        Member member = library.findMember("00001X");
        Assert.assertEquals(
                new Member("00001X", "Bob", "Esponja", 1, 28000),
                member);
    }


    @Test(expected = MemberNotFoundException.class)
    public void findMemberNotFoundTest() throws MemberNotFoundException {
        Member member = library.findMember("sdflkjfsdlkj");
    }

    @Test
    public void existBookLendTest() throws BookNotFoundException, MemberNotFoundException {
        boolean exists = library.existLend("00001", "00001X");
        Assert.assertTrue(exists);
    }

    @Test
    public void notExistBookLendTest() throws BookNotFoundException, MemberNotFoundException {
        boolean exists = library.existLend("00001", "00002Y");
        Assert.assertFalse(exists);
    }

    @Test(expected = MemberNotFoundException.class)
    public void existBookLendMemberNotFoundTest() throws BookNotFoundException, MemberNotFoundException {
        library.existLend("00001", "00002");
    }

    @Test(expected = BookNotFoundException.class)
    public void existBookLendBookNotFoundTest() throws BookNotFoundException, MemberNotFoundException {
        library.existLend("000021", "00002Y");
    }
}
