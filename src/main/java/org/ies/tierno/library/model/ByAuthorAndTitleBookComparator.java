package org.ies.tierno.library.model;

import java.util.Comparator;

public class ByAuthorAndTitleBookComparator implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        int compare = o1.getAuthor().compareTo(o2.getAuthor());
        if (compare == 0) {
            compare = o1.getTitle().compareTo(o2.getTitle());
            if (compare == 0) {
                compare = o1.getIsbn().compareTo(o2.getIsbn());
            }
        }
        return compare;
    }
}
