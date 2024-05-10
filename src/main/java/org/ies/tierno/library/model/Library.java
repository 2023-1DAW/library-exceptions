package org.ies.tierno.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.ies.tierno.library.exceptions.BookNotFoundException;
import org.ies.tierno.library.exceptions.MemberNotFoundException;

import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class Library {
    private String name;
    private TreeSet<Book> books;
    private List<Member> members;
    private List<BookLend> bookLends;

    public List<String> findAuthorTitles(String author) {
        return books
                .stream()
                .filter(book -> book.getAuthor().equals(author))
                .map(book -> book.getTitle())
                .collect(Collectors.toList());
    }

    public Book findBook(String isbn) throws BookNotFoundException {
        for(var book:books) {
            if(book.getIsbn().equalsIgnoreCase(isbn)) {
                return book;
            }
        }
        throw new BookNotFoundException(isbn);
    }

    public Member findMember(String nif) throws MemberNotFoundException {

        for(var member: members) {
            if(member.getNif().equalsIgnoreCase(nif)) {
                return member;
            }
        }
        throw new MemberNotFoundException(nif);
    }

    public boolean existLend(String isbn, String nif) throws BookNotFoundException, MemberNotFoundException {
        findMember(nif);
        findBook(isbn);
        for(var bookLend : bookLends) {
            if(bookLend.getIsbn().equalsIgnoreCase(isbn) && bookLend.getMemberNif().equalsIgnoreCase(nif)) {
                return true;
            }
        }
        return false;
    }
}
