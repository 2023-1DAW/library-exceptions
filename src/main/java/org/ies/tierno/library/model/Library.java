package org.ies.tierno.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Library {
    private String name;
    private List<Book> books;
    private List<Member> members;
    private List<BookLend> bookLends;

}
