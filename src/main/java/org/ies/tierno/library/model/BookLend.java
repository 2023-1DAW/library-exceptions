package org.ies.tierno.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class BookLend {
    private String isbn;
    private String memberNif;
    private Date lendDate;
    private Date returnDate;
}
