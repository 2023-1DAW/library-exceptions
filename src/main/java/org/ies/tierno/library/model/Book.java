package org.ies.tierno.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Data
@AllArgsConstructor
public class Book implements Comparable<Book> {
    private String isbn;
    private String title;
    private String author;
    private List<String> genres;

    public boolean hasGenre(String genre) {
        return genres.contains(genre);
    }

    @Override
    public int compareTo(Book o) {
        int compare = this.author.compareTo(o.getAuthor());
        if (compare == 0) {
            compare = this.title.compareTo(o.getTitle());
            if (compare == 0) {
                compare = this.isbn.compareTo(o.getIsbn());
            }
        }
        return compare;
    }


    public Optional<Double> average(List<Double> numbers) {
        return numbers
                .stream()
                .reduce((a, b) -> a + b)
                .map(sum -> sum / numbers.size());
    }
}
