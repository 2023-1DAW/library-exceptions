package org.ies.tierno.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.ies.tierno.library.exceptions.BookNotFoundException;

import java.util.List;

@Data
@AllArgsConstructor
public class Library {
    private String name;
    private List<Book> books;
    private List<Member> members;
    private List<BookLend> bookLends;

    public Book findBook(String isbn) throws BookNotFoundException {
        for(var book:books) {
            if(book.getIsbn().equalsIgnoreCase(isbn)) {
                return book;
            }
        }
        throw new BookNotFoundException(isbn);
    }
}
