package org.ies.tierno.library.components;

import lombok.AllArgsConstructor;
import org.ies.tierno.library.model.BookLend;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

@AllArgsConstructor
public class BookLendReader implements Reader<BookLend> {

    private static final Logger log = LoggerFactory.getLogger(BookLendReader.class);
    private final Scanner scanner;


    @Override
    public BookLend read() {
        log.info("Introduce los datos del préstamo");
        log.info("ISBN:");
        String isbn = scanner.nextLine();

        Date lendDate = readDate("Fecha de préstamo (dd/mm/yyyy):");

        log.info("NIF socio:");
        String nif = scanner.nextLine();

        Date returnDate = readDate("Fecha de devolución (dd/mm/yyyy):");
        return new BookLend(isbn, nif, lendDate, returnDate);
    }

    private Date readDate(String message){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date lendDate = null;
        do {
            try {
                log.info(message);
                String lendDateStr = scanner.nextLine();
                lendDate = sdf.parse(lendDateStr);
            } catch (ParseException e) {
                log.info("Formato de fecha inválido, debe seguir el formato dd/mm/yyyy");
            }
        } while (lendDate == null);
        return lendDate;
    }
}
